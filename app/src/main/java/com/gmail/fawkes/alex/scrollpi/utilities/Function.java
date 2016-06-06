package com.gmail.fawkes.alex.scrollpi.utilities;

@SuppressWarnings("unused") // avoid implementing each specific case on first use
class Function {
    private Function() {
    }

    public interface Void {
        void execute();
    }

    public interface Void1<P> {
        void execute(P p);
    }

    public interface Void2<P, P1> {
        void execute(P p, P1 p1);
    }

    public interface Void3<P, P1, P2> {
        void execute(P p, P1 p1, P2 p2);
    }

    public interface Void4<P, P1, P2, P3> {
        void execute(P p, P1 p1, P2 p2, P3 p3);
    }

    public interface Void5<P, P1, P2, P3, P4> {
        void execute(P p, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    public interface Void6<P, P1, P2, P3, P4, P5> {
        void execute(P p, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    public interface Return<R> {
        R execute();
    }

    public interface Return1<R, P> {
        R execute(P p);
    }

    public interface Return2<R, P, P1> {
        R execute(P p, P1 p1);
    }

    public interface Return3<R, P, P1, P2> {
        R execute(P p, P1 p1, P2 p2);
    }

    public interface Return4<R, P, P1, P2, P3> {
        R execute(P p, P1 p1, P2 p2, P3 p3);
    }

    public interface Return5<R, P, P1, P2, P3, P4> {
        R execute(P p, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    public interface Return6<R, P, P1, P2, P3, P4, P5> {
        R execute(P p, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }
}
