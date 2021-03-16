package com.laz.knowledge.seventytwo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.Binder;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Scopes;

public class Demo2 {
	@BindingAnnotation
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
	public @interface DefaultAnnotation  {
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BindModule());
		DogEgg dogEgg = injector.getInstance(DogEgg.class);
		System.out.println(dogEgg.service);
		System.out.println(dogEgg.service2);
	}

	interface Service {
	}

	public  static class DefaultService implements Service {
	}

	public  static class DefaultService2 implements Service {
	}

	public static class DogEgg {
		@Inject
		@DefaultAnnotation // 使用自定义注解标识该字段需要DefaultService实例
		public Service service;

		@Inject // 没有自定义注解，那就注入DefaultService2实例
		public Service service2;
	}

	public static class BindModule implements Module {
		@Override
		public void configure(Binder binder) {
			binder.bind(Service.class).annotatedWith(DefaultAnnotation.class).to(DefaultService.class)
					.in(Scopes.SINGLETON);

			binder.bind(Service.class).to(DefaultService2.class);
		}
	}
}
