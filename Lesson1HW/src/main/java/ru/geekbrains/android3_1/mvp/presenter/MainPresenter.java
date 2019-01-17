package ru.geekbrains.android3_1.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.geekbrains.android3_1.R;
import ru.geekbrains.android3_1.mvp.model.Model;
import ru.geekbrains.android3_1.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;
    private ButtonCounterIds buttonCounterIds;



    public MainPresenter(ButtonCounterIds buttonCounterIds) {
        this.model = new Model();
        this.buttonCounterIds = buttonCounterIds;
    }

    public int calculateButtonValue(int index){
        model.setAt(index, model.getAt(index) + 1);
        return model.getAt(index);
    }

    public void counterClick(int id){
        if (id == buttonCounterIds.getBtnCounter1Id()){
            getViewState().setButtonValue(0, calculateButtonValue(0));
        } else if (id == buttonCounterIds.getBtnCounter2Id()) {
            getViewState().setButtonValue(1, calculateButtonValue(1));
        } else if (id == buttonCounterIds.getBtnCounter3Id()){
            getViewState().setButtonValue(2, calculateButtonValue(2));
        }
    }
}
