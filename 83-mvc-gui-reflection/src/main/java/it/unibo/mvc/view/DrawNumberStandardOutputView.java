package it.unibo.mvc.view;

import java.util.Objects;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public final class DrawNumberStandardOutputView implements DrawNumberView{

    @Override
    public void setController(DrawNumberController observer) {

    }

    @Override
    public void start() {

    }

    @Override
    public void result(DrawResult res) {
        Objects.requireNonNull(res);
        IO.println(res.getDescription());

    }
    
}
