package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
public class Triangle {
    Vec2 a;
    Vec2 b;
    Vec2 c;



    Triangle(Vec2 a,Vec2 b,Vec2 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static Triangle getRandomTriangle() {
        Random r = new Random();
        double nx1 =r.nextDouble()* 2 - 1;
        double ny1 = (double) r.nextDouble()* 2 - 1;
        double nx2 = (double) r.nextDouble()* 2 - 1;
        double ny2 = (double) r.nextDouble()* 2 - 1;
        double nx3 = (double) r.nextDouble()* 2 - 1;
        double ny3 = (double) r.nextDouble()* 2 - 1;
        return new Triangle(new Vec2(nx1,ny1),new Vec2(nx2,ny2),new Vec2(nx3,ny3));
    }

    public void render(GL2 gl){
        Figures.renderTriangle(gl,a,b,c,true);
    }

}
