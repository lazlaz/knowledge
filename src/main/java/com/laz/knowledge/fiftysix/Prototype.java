package com.laz.knowledge.fiftysix;

//原型模式-深度拷贝
public class Prototype {
	public class DeepCloneableTarget implements Cloneable {

		private String cloneName;

		private String cloneClass;

		public DeepCloneableTarget(String cloneName, String cloneClass) {
			this.cloneName = cloneName;
			this.cloneClass = cloneClass;
		}

		// 因为该类的属性都是String，因此我们这里使用默认的clone完成即可
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

	public class DeepProtoType implements Cloneable {

		public String name;
		public DeepCloneableTarget deepCloneableTarget;

		public DeepProtoType() {
			super();
		}

		// 深拷贝-方式1
		@Override
		protected Object clone() throws CloneNotSupportedException {

			Object deep = null;
			// 完成对属性为基本数据类型和String的克隆
			deep = super.clone();
			// 对属性为引用类型的单独处理
			DeepProtoType deepProtoType = (DeepProtoType) deep;
			deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();

			return deepProtoType;
		}
	}

	public static void main(String[] args) throws Exception {
		Prototype pt = new Prototype();
		DeepProtoType p = pt.new DeepProtoType();
		p.name = "宋江";
		p.deepCloneableTarget = pt.new DeepCloneableTarget("小明", "学生类");

		// 测试，方式1
		DeepProtoType p1 = (DeepProtoType) p.clone();

		System.out.println("p.name=" + p.name + " p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
		System.out.println("p1.name=" + p.name + " p2.deepCloneableTarget=" + p1.deepCloneableTarget.hashCode());


	}
}
