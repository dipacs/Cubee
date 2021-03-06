package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;

/**
 *
 * @author dipacs
 */
public final class Led extends AUserControl {
    
    public static class StyleClass<T extends Led> extends AUserControl.StyleClass<T> {
        
        private final Style<Color> color = new Style<Color>(null, false);
        private final Style<Integer> size = new Style<Integer>(null, false);

        @Override
        public void apply(T component) {
            super.apply(component);
            
            color.apply(component.colorProperty());
            size.apply(component.sizeProperty());
        }

        public Style<Color> getColor() {
            return color;
        }

        public Style<Integer> getSize() {
            return size;
        }
        
    } 

    private final BooleanProperty light = new BooleanProperty(false, false, false);
    private final Property<Color> color = new Property<Color>(Color.getRgbColor(0x00c0ff), false, false);
    private final IntegerProperty size = new IntegerProperty(20, false, false);
    private Panel ledPanel;

    public Led() {
        widthProperty().bind(size);
        heightProperty().bind(size);

        light.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                refreshColors();
            }
        });
        color.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                refreshColors();
            }
        });

        LinearGradient lg = new LinearGradient(0.0, new ColorStop(0.0, Color.getArgbColor(0x40000000)),
                new ColorStop(1.0, Color.getArgbColor(0x40ffffff)));
        this.backgroundProperty().set(lg);

        this.ledPanel = new Panel();
        this.ledPanel.translateXProperty().set(2);
        this.ledPanel.translateYProperty().set(2);
        this.ledPanel.widthProperty().bind(new AExpression<Integer>() {
            {
                this.bind(sizeProperty());
            }

            @Override
            public Integer calculate() {
                return sizeProperty().get() - 6;
            }
        });
        this.ledPanel.heightProperty().bind(this.ledPanel.widthProperty());
        this.ledPanel.pointerTransparentProperty().set(true);
        this.getChildren().add(ledPanel);

        size.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                borderProperty().set(new Border(1, Color.getRgbColor(0xe0e0e0), (size.get() + 2) / 2));
                ledPanel.borderProperty().set(new Border(1, Color.getRgbColor(0x808080), (size.get() - 4) / 2));
            }
        });
        size.invalidate();

        refreshColors();
    }

    public BooleanProperty lightProperty() {
        return light;
    }

    public Property<Color> colorProperty() {
        return color;
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    private void refreshColors() {
        LinearGradient lgLed;
        if (light.get()) {
            lgLed = new LinearGradient(0.0, new ColorStop(0.0, Color.fadeColors(color.get(), Color.WHITE, 0.5)),
                    new ColorStop(0.5, color.get()),
                    new ColorStop(1.0, Color.fadeColors(color.get(), Color.BLACK, 0.25)));
        } else {
            lgLed = new LinearGradient(0.0, new ColorStop(0.0, Color.getRgbColor(0xe0e0e0)),
                    new ColorStop(1.0, Color.getRgbColor(0xc0c0c0)));
        }
        this.ledPanel.backgroundProperty().set(lgLed);
    }
}
