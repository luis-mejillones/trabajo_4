package util;

public class Checked {
    @FunctionalInterface
    public interface Action {
        void execute() throws Exception;
    }

    @FunctionalInterface
    public interface Command<T> {
        void execute(T input) throws Exception;
    }

    @FunctionalInterface
    public interface BiCommand<TInputA, TInputB> {
        void execute(TInputA inputA, TInputB inputB) throws Exception;
    }

    @FunctionalInterface
    public interface Supplier<TOutput> {
        TOutput get() throws Exception;
    }

    @FunctionalInterface
    public interface Query<TInput, TOutput> {
        TOutput get(TInput input) throws Exception;
    }

    @FunctionalInterface
    public interface Mapper<TInput, TOutput> {
        TOutput map(TInput input) throws Exception;
    }

    @FunctionalInterface
    public interface BiQuery<TInputA, TInputB, TOutput> {
        TOutput get(TInputA inputA, TInputB inputB) throws Exception;
    }

    @FunctionalInterface
    public interface Predicate<TInput> {
        boolean test(TInput input) throws Exception;
    }

    @FunctionalInterface
    public interface BiPredicate<TInputA, TInputB> {
        boolean test(TInputA inputA, TInputB inputB) throws Exception;
    }

    @FunctionalInterface
    public interface IOCommand<TInput, TOutput> {
        TOutput execute(TInput input) throws Exception;
    }

    @FunctionalInterface
    public interface IOBiCommand<TInputA, TInputB, TOutput> {
        TOutput execute(TInputA inputA, TInputB inputB) throws Exception;
    }
}
