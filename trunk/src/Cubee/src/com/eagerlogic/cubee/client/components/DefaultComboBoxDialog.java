package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.styles.Border;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ColorBackground;
import com.eagerlogic.cubee.client.styles.ECursor;
import com.eagerlogic.cubee.client.styles.EHAlign;
import com.eagerlogic.cubee.client.styles.ETextAlign;
import com.eagerlogic.cubee.client.styles.Padding;

/**
 *
 * @author dipacs
 */
public final class DefaultComboBoxDialog<T> extends AComboBoxDialog<T> {

	private VBox vbox;
	private int result = -1;

	public DefaultComboBoxDialog() {
		super(false);
		
		
	}

	private AComponent createItem(String title, final int result, final VBox root) {
		Label label = new Label();
		label.paddingProperty().set(new Padding(5));
		label.textProperty().set(title.toString());
		label.minWidthProperty().bind(new AExpression<Integer>() {
			
			{
				this.bind(root.clientWidthProperty());
			}

			@Override
			public Integer calculate() {
				return root.clientWidthProperty().get() - 10;
			}
		});
		label.textAlignProperty().set(ETextAlign.CENTER);
		label.cursorProperty().set(ECursor.POINTER);
		label.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

			@Override
			public void onFired(ClickEventArgs args) {
				DefaultComboBoxDialog.this.result = result;
				close();
			}
		});
		return label;
	}

	@Override
	protected int onClose() {
		return result;
	}

	@Override
	protected void init(int selectedIndex, T[] items, boolean emptySelectionEnabled, String emptySelectionText) {
		Panel root = new Panel();
		root.backgroundProperty().set(new ColorBackground(Color.WHITE));
		root.borderProperty().set(new Border(1, Color.getRgbColor(0x202020), 5));
		this.setRootComponent(root);
		
		this.vbox = new VBox();
		root.getChildren().add(vbox);
		
		if (emptySelectionEnabled) {
			vbox.getChildren().add(createItem(emptySelectionText, -1, vbox));
		}
		
		int i = 0;
		for (T item : items) {
			vbox.getChildren().add(createItem(item.toString(), i, vbox));
			i++;
		}
		
		vbox.getChildren().add(null);
		vbox.setCellHeight(vbox.getChildren().size() - 1, 10);
		
		Panel btnPanel = new Panel();
		btnPanel.paddingProperty().set(new Padding(5));
		
		Button btnCancel = new Button();
		btnCancel.textProperty().set("Cancel");
		btnCancel.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

			@Override
			public void onFired(ClickEventArgs args) {
				cancel();
			}
		});
		btnPanel.getChildren().add(btnCancel);
		vbox.getChildren().add(btnPanel);
		vbox.setCellHAlign(vbox.getChildren().size() - 1, EHAlign.CENTER);
	}
}