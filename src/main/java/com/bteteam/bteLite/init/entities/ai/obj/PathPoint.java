package com.bteteam.bteLite.init.entities.ai.obj;

public class PathPoint {

	public double x, y, z;

	public PathPoint(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PathPoint) {
			PathPoint point = (PathPoint) obj;
			return point.x == x && point.y == y && point.z == z;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) Math.floor(3 * 3 * x * y * z);
	}

}