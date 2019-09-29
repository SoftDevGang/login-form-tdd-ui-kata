package org.codecop.swing;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class AllDocumentListener implements DocumentListener {

    @Override
    public void removeUpdate(@SuppressWarnings("unused") DocumentEvent e) {
        fire();
    }

    @Override
    public void insertUpdate(@SuppressWarnings("unused") DocumentEvent e) {
        fire();
    }

    @Override
    public void changedUpdate(@SuppressWarnings("unused") DocumentEvent e) {
        fire();
    }

    protected abstract void fire();

}
