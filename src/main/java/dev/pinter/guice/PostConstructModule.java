package dev.pinter.guice;

/*
 * MIT License
 *
 * Copyright (c) 2025 Emerson Pinter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.ProvisionListener;
import jakarta.annotation.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Guice module to support execution of methods annotated with @{@link PostConstruct} after the injection.
 */
public class PostConstructModule extends AbstractModule {
    @Override
    protected void configure() {
        bindListener(Matchers.any(), new PostConstructListener());
    }

    private static class PostConstructListener implements ProvisionListener {
        @Override
        public <T> void onProvision(ProvisionInvocation<T> provision) {
            T obj = provision.provision();
            List<Method> postConstructs = new ArrayList<>();
            Class<?> clazz = obj.getClass();
            while (clazz != Object.class) {
                Arrays.stream(clazz.getDeclaredMethods())
                        .filter(declMethod -> declMethod.isAnnotationPresent(PostConstruct.class)
                                && postConstructs.stream()
                                .noneMatch(p -> declMethod.getName().equals(p.getName())
                                        && declMethod.getParameterCount() == 0
                                        && p.getParameterCount() == 0
                                )
                        ).forEach(postConstructs::add);
                clazz = clazz.getSuperclass();
            }

            postConstructs.reversed().forEach(invokeMethod(obj));
        }

        private <T> Consumer<Method> invokeMethod(T obj) {
            return m -> {
                try {
                    if (!m.trySetAccessible()) {
                        throw new IllegalAccessException("Can't access method " + m.getName() + ", class " + obj.getClass().getName());
                    }
                    if (m.getParameterCount() == 0 && m.getReturnType().equals(Void.TYPE)) {
                        m.invoke(obj);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Error invoking @PostConstruct  " + m.getName(), e);
                }
            };
        }
    }
}
