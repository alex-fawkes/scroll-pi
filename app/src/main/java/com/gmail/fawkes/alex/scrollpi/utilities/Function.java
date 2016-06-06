package com.gmail.fawkes.alex.scrollpi.utilities;

// TODO: remove "I" prefix from interfaces

@SuppressWarnings("unused") // avoid implementing each specific case on first use
final class Function {
    private Function() {
    }

    public interface Void {
        void execute();
    }

    public interface Void1<P> {
        void execute(final P p);
    }

    public interface Void2<P, P1> {
        void execute(final P p, final P1 p1);
    }

    public interface Void3<P, P1, P2> {
        void execute(final P p, final P1 p1, final P2 p2);
    }

    public interface Void4<P, P1, P2, P3> {
        void execute(final P p, final P1 p1, final P2 p2, final P3 p3);
    }

    public interface Void5<P, P1, P2, P3, P4> {
        void execute(final P p, final P1 p1, final P2 p2, final P3 p3, final P4 p4);
    }

    public interface Void6<P, P1, P2, P3, P4, P5> {
        void execute(final P p, final P1 p1, final P2 p2, final P3 p3, final P4 p4, final P5 p5);
    }

    public interface Return<R> {
        R execute();
    }

    public interface Return1<R, P> {
        R execute(final P p);
    }

    public interface Return2<R, P, P1> {
        R execute(final P p, final P1 p1);
    }

    public interface Return3<R, P, P1, P2> {
        R execute(final P p, final P1 p1, final P2 p2);
    }

    public interface Return4<R, P, P1, P2, P3> {
        R execute(final P p, final P1 p1, final P2 p2, final P3 p3);
    }

    public interface Return5<R, P, P1, P2, P3, P4> {
        R execute(final P p, final P1 p1, final P2 p2, final P3 p3, final P4 p4);
    }

    public interface Return6<R, P, P1, P2, P3, P4, P5> {
        R execute(final P p, final P1 p1, final P2 p2, final P3 p3, final P4 p4, final P5 p5);
    }
}
