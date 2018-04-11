package origin.com.effectivejava.builder;

/**
 * 带有Builder实例的方法通常利用有限制的通配符类型来约束构建器的类型参数。
 * 例如， 下面就是构建每个节点的方法，它利用一个客户端提供的Builder实例来构建树：
 * Tree buildTree(Builder<? extends Node> nodeBuilder) { ... }
 * Created by zc on 2018/4/4.
 */

public interface Builder<T extends Object> {
    public T build();
}
