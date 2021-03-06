package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.utils.Rectangle;
import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class RectangleProperty extends Property<Rectangle> {

    public RectangleProperty() {
    }

    public RectangleProperty(Rectangle defaultValue) {
        super(defaultValue);
    }

    public RectangleProperty(Rectangle defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public RectangleProperty(Rectangle defaultValue, AValidator<Rectangle> validator) {
        super(defaultValue, validator);
    }

    public RectangleProperty(Rectangle defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public RectangleProperty(Rectangle defaultValue, boolean nullable, boolean readonly, AValidator<Rectangle> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public Rectangle animate(double pos, Rectangle startValue, Rectangle endValue) {
        if (startValue == endValue) {
            return startValue;
        }
        if (startValue == null) {
            startValue = new Rectangle(0, 0, 1000, 1000);
        }
        if (endValue == null) {
            endValue = new Rectangle(0, 0, 1000, 1000);
        }

        return new Rectangle(animateInt(startValue.getX(), endValue.getX(), pos),
                animateInt(startValue.getY(), endValue.getY(), pos),
                animateInt(startValue.getWidth(), endValue.getWidth(), pos),
                animateInt(startValue.getHeight(), endValue.getHeight(), pos)
        );
    }

    private int animateInt(int startValue, int endValue, double pos) {
        return (int) (startValue + ((endValue - startValue) * pos));
    }

}
