package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public class BooleanProperty extends Property<Boolean> {

    public BooleanProperty() {
    }

    public BooleanProperty(Boolean defaultValue) {
        super(defaultValue);
    }

    public BooleanProperty(Boolean defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public BooleanProperty(Boolean defaultValue, AValidator<Boolean> validator) {
        super(defaultValue, validator);
    }

    public BooleanProperty(Boolean defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public BooleanProperty(Boolean defaultValue, boolean nullable, boolean readonly, AValidator<Boolean> validator) {
        super(defaultValue, nullable, readonly, validator);
    }
    
    public final BooleanProperty not() {
    	final BooleanProperty res = new BooleanProperty();
    	res.bind(new AExpression<Boolean>() {
    		
    		{
    			bind(res);
    		}

			@Override
			public Boolean calculate() {
				if (BooleanProperty.this.get() == null || BooleanProperty.this.get() == false) {
					return true;
				}
				return false;
			}
		});
    	return res;
    }

}
