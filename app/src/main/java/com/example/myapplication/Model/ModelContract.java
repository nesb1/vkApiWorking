package com.example.myapplication.Model;

public interface ModelContract {

    interface OnFinishedListener {
        void onFinishedNetworking(ResponseVk.VkUser vkUser);

        void onFailureNetworking(Throwable t);
    }
}
