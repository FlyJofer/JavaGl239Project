package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static problem.TriangleType.*;

public class Problem {
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество треугольников. Найти такие два треугольника, что\n" +
            "площадь фигуры, находящейся внутри обоих треугольников, будет максимальна.";

    public static final String PROBLEM_CAPTION = "Итоговый проект ученика Всеволода Колпачкова (10-2)";

    private static final String POLYGON_FILE_NAME = "polygon.txt";
    private static final String TRIANGLES_FILE_NAME = "triangles.txt";

    private final ArrayList<Triangle> triangles;
    Polygon polygon;

    public Problem() {
        triangles = new ArrayList<>();
        polygon = new Polygon();
    }

    public void addTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        triangles.add(new Triangle(new Vec2(x1, y1), new Vec2(x2, y2), new Vec2(x3, y3)));
    }

    public void solve() {
        Triangle tri1 = new Triangle(), tri2 = new Triangle();

        for (Triangle tri : triangles)
            tri.setType(UNUSED);

        for (Triangle first : triangles) {
            for (Triangle second : triangles)
            {
                if (first == second)
                    continue;

                Polygon p = first.intersect(second);
                if (p.area > polygon.area) {
                    polygon = p;
                    tri1 = first;
                    tri2 = second;
                }
            }
        }

        for (Triangle tri : triangles)
            if (tri == tri1 || tri == tri2)
                tri.setType(CHOOSEN);
    }

    public void loadFromFile() {
        clear();

        // Загрузка треугольников
        try {
            File file = new File(TRIANGLES_FILE_NAME);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Triangle tri = new Triangle(
                        new Vec2(sc.nextDouble(), sc.nextDouble()),
                        new Vec2(sc.nextDouble(), sc.nextDouble()),
                        new Vec2(sc.nextDouble(), sc.nextDouble())
                );

                sc.nextLine();

                tri.setType(TriangleType.valueOf(sc.nextLine()));
                triangles.add(tri);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }

        // Загрузка полигона
        try {
            File file = new File(POLYGON_FILE_NAME);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                polygon.points.add(new Vec2(sc.nextDouble(), sc.nextDouble()));
                sc.nextLine();
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    public void saveToFile() {
        // Сохранение треугольников
        try {
            PrintWriter out = new PrintWriter(new FileWriter(TRIANGLES_FILE_NAME));
            for (Triangle tri : triangles) {
                for (Vec2 point : tri.points)
                    out.printf("%.4f %.4f ", point.x, point.y);
                out.printf("\n");

                out.println(tri.type);
            }

            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }

        // Сохранение полигона
        try {
            PrintWriter out = new PrintWriter(new FileWriter(POLYGON_FILE_NAME));
            for (Vec2 point : polygon.points)
                out.printf("%.4f %.4f\n", point.x, point.y);

            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    public void addRandomTriangles(int n) {
        polygon = new Polygon();
        for (Triangle tri : triangles)
            tri.setType(UNKNOWN);


        for (int i = 0; i < n; i++) {
            Triangle tri = new Triangle();
            tri.randomize();

            triangles.add(tri);
        }
    }

    public void clear() {
        triangles.clear();
        polygon = new Polygon();
    }

    public void render(GL2 gl) {
        for (Triangle tri : triangles)
            if (tri.type != CHOOSEN)
                tri.render(gl);

        for (Triangle tri : triangles)
            if (tri.type == CHOOSEN)
                tri.render(gl);

        polygon.render(gl);
    }
}
