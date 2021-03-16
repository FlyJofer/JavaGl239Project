package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
public class Triangle {
    Vector2 a;
    Vector2 b;
    Vector2 c;



    Triangle(Vector2 a,Vector2 b,Vector2 c) {
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
        return new Triangle(new Vector2(nx1,ny1),new Vector2(nx2,ny2),new Vector2(nx3,ny3));
    }

    public void render(GL2 gl){
        Figures.renderTriangle(gl,a,b,c,true);
    }

}
