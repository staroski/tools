package br.com.staroski.tools.collections;

/**
 * @author Ricardo Artur Staroski
 */
public abstract class ProcessorAdapter<T> implements Processor<T> {

    @Override
    public void afterFinish() {}

    @Override
    public void beforeStart() {}

    @Override
    public <V> V getResult() {
        return null;
    }
}
