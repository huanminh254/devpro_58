package com.devpro.ard_58_nguyenminhhuan_day07.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.devpro.ard_58_nguyenminhhuan_day07.model.Option;
import com.devpro.ard_58_nguyenminhhuan_day07.model.itemFood;
import java.util.List;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData<List<itemFood>> listFood = new MutableLiveData<>();
    private final MutableLiveData<itemFood> selectedFood = new MutableLiveData<>();
    public LiveData<List<itemFood>> getListFood(){
        return listFood;
    }
    public LiveData<itemFood> getSelectedFood(){
        return selectedFood;
    }
    public void setListFood(List<itemFood> itemFood){
        listFood.setValue(itemFood);
    }
    public void setSelectedFood(itemFood itemFood){
        selectedFood.setValue(itemFood);
    }
    public void updateListFood(itemFood updateFood){
        List<itemFood> temp = listFood.getValue();
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).getName().equals(updateFood.getName())){
                temp.set(i, updateFood);
                listFood.setValue(temp);
                break;
            }
        }
    }

}
