package com.laz.knowledge.seventytwo;


import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

//简单的注入
public class Demo1 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BindModule());
		DogEgg dogEgg = injector.getInstance(DogEgg.class);
		System.out.println(dogEgg.service);
	}

	interface Service {
	}

	public static class DefaultService implements Service {
	}

	public static class DogEgg {
		@Inject // 告诉Guice，这里要注入东西，具体的注入规则从Module里找吧
		public Service service;
	}

	public static class BindModule implements Module {
		@Override
		public void configure(Binder binder) {
			// 在注入的时候，遇到Service接口类型，全部注入成DefaultService实例
			binder.bind(Service.class).to(DefaultService.class);
		}
	}
}
