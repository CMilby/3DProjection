package me.cmilby;

public final class Util {

	private Util() {
		
	}
	
	public static boolean isNumeric(String str) {
 		try {
        	Double.parseDouble(str);
        	return true;
    	} catch (NumberFormatException nfe) {
    	
    	}
    	return false;
	}
	
	public static Point3D rotateAll(double[] c, double[] pIn, double[] aIn) {
		double[] p = new double[3];
		double[] a = new double[3];

		double[] sin = new double[3];
		double[] cos = new double[3];

		for (int i = 0; i < 3; i++) {
			p[i] = pIn[i] - c[i];
			a[i] = Math.toRadians(aIn[i]);
			sin[i] = Math.sin(a[i]);
			cos[i] = Math.cos(a[i]);
		}

		double f = sin[1] * (p[1] * sin[0] + p[2] * cos[0]) + p[0] * cos[1];
		double g = p[1] * cos[0] - p[2] * sin[0];

		double[] answer = {
			f * cos[2] - g * sin[2] + c[0],
			f * sin[2] + g * cos[2] + c[1],
			cos[1] * (p[1] * sin[0] + p[2] * cos[0]) - p[0] * sin[1] + c[2]
		};
		return new Point3D(answer[0], answer[1], answer[2]);
	}
	
	public static Point3D rotate(double[] points, double[] centers, double[] rotations) {
		final double X_ROTATE = Math.toRadians(rotations[0]);
		final double Y_ROTATE = Math.toRadians(rotations[1]);
		final double Z_ROTATE = Math.toRadians(rotations[2]);
		double[][] array = 
				{{Math.cos(Y_ROTATE) * Math.cos(Z_ROTATE), 
						Math.cos(Z_ROTATE) * Math.sin(X_ROTATE) * Math.sin(Y_ROTATE) - Math.cos(X_ROTATE) * Math.sin(Z_ROTATE), 
						Math.cos(X_ROTATE) * Math.cos(Z_ROTATE) * Math.sin(Y_ROTATE) + Math.sin(X_ROTATE) * Math.sin(Z_ROTATE)},
				{Math.cos(Y_ROTATE) * Math.sin(Z_ROTATE), 
						Math.cos(X_ROTATE) * Math.cos(Z_ROTATE) + Math.sin(X_ROTATE) * Math.sin(Y_ROTATE) + Math.sin(Z_ROTATE),
						-Math.cos(Z_ROTATE) * Math.sin(X_ROTATE) + Math.cos(X_ROTATE) * Math.sin(Y_ROTATE) * Math.sin(Z_ROTATE)},
				{-Math.sin(Y_ROTATE), Math.cos(Y_ROTATE) * Math.sin(X_ROTATE), Math.cos(X_ROTATE) * Math.cos(Y_ROTATE)}};
		double[] solution = new double[3];
		for (int x = 0; x < array.length; x++) {
			double total = 0;
			for (int y = 0; y < array[x].length; y++) 
				total += (array[x][y] * (points[y] - centers[y]));
			solution[x] = total + centers[x];
		}
		return (new Point3D(solution[0], solution[1], solution[2]));
	}
	
	public static Point3D rotateX(double xP, double yP, double zP, double angle, double xC, double yC, double zC) {
		double[][] array =
				{{1, 0, 0},
				{0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle))},
				{0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle))}};
		double[] points = {xP - xC, yP - yC, zP - zP};
		double[] solution = new double[3];
		double[] centers = {xC , yC, zC};
		for (int x = 0; x < array.length; x++) {
			double total = 0;
			for (int y = 0; y < array[x].length; y++)
				total += (array[x][y] * points[y]);
			solution[x] = total + centers[x];
		}
		return (new Point3D(solution[0], solution[1], solution[2]));
	}

	public static Point3D rotateY(double xP, double yP, double zP, double angle, double xC, double yC, double zC) {
		double[][] array =
				{{Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle))},
				{0, 1, 0},
				{-Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle))}};
		double[] points = {xP - xC, yP - yC, zP - zC};
		double[] solution = new double[3];
		double[] centers = {xC, yC, zC};
		for (int x = 0; x < array.length; x++) {
			 double total = 0;
			 for (int y = 0; y < array[x].length; y++)
			 	total += (array[x][y] * points[y]);
			 solution[x] = total + centers[x];
		}
		return (new Point3D(solution[0], solution[1], solution[2]));
	}
	
	public static Point3D rotateZ(double xP, double yP, double zP, double angle, double xC, double yC, double zC) {
		double[][] array =
				{{Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
				{Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
				{0, 0, 1}};
		double[] points = {xP - xC, yP - yC, zP - zC};
		double[] solution = new double[3];
		double[] centers = {xC, yC, zC};
		for (int x = 0; x < array.length; x++) {
			double total = 0;
			for (int y = 0; y < array[x].length; y++)
				total += (array[x][y] * points[y]);
			solution[x] = total + centers[x];
		}
		return (new Point3D(solution[0], solution[1], solution[2]));
	}
}
