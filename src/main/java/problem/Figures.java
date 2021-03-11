package problem;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
import java.util.Scanner;
public class Figures {
    public static void renderPoint(GL2 gl, Vector2 pos, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }
    public static void renderLine(GL2 gl, Vector2 pos, float size) {
        gl.glPointSize(size);
        gl.glLineWidth(8);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(pos.x, pos.y);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }
    public static void renderTriangle(GL2 gl, Vector2 pos, float size) {
        gl.glPointSize(size);
        gl.glLineWidth(7);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(pos.x, pos.y);
        gl.glVertex2d(pos.x, pos.y);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }
    public static void renderSquare(GL2 gl, Vector2 pos, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }
}
