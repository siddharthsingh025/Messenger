// Generated by view binder compiler. Do not edit!
package com.siddharth.massengerapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.siddharth.massengerapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChatBoxBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView backArrow;

  @NonNull
  public final RecyclerView charBoxRecyclerView;

  @NonNull
  public final Toolbar chatBoxToolbar;

  @NonNull
  public final EditText enterMsg;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final LinearLayout linear;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final ImageView send;

  @NonNull
  public final TextView userName;

  private ActivityChatBoxBinding(@NonNull RelativeLayout rootView, @NonNull ImageView backArrow,
      @NonNull RecyclerView charBoxRecyclerView, @NonNull Toolbar chatBoxToolbar,
      @NonNull EditText enterMsg, @NonNull ImageView imageView4, @NonNull ImageView imageView5,
      @NonNull ImageView imageView6, @NonNull LinearLayout linear,
      @NonNull CircleImageView profileImage, @NonNull ImageView send, @NonNull TextView userName) {
    this.rootView = rootView;
    this.backArrow = backArrow;
    this.charBoxRecyclerView = charBoxRecyclerView;
    this.chatBoxToolbar = chatBoxToolbar;
    this.enterMsg = enterMsg;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.linear = linear;
    this.profileImage = profileImage;
    this.send = send;
    this.userName = userName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChatBoxBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChatBoxBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chat_box, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChatBoxBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backArrow;
      ImageView backArrow = ViewBindings.findChildViewById(rootView, id);
      if (backArrow == null) {
        break missingId;
      }

      id = R.id.charBoxRecyclerView;
      RecyclerView charBoxRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (charBoxRecyclerView == null) {
        break missingId;
      }

      id = R.id.chatBoxToolbar;
      Toolbar chatBoxToolbar = ViewBindings.findChildViewById(rootView, id);
      if (chatBoxToolbar == null) {
        break missingId;
      }

      id = R.id.enterMsg;
      EditText enterMsg = ViewBindings.findChildViewById(rootView, id);
      if (enterMsg == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.linear;
      LinearLayout linear = ViewBindings.findChildViewById(rootView, id);
      if (linear == null) {
        break missingId;
      }

      id = R.id.profile_image;
      CircleImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.send;
      ImageView send = ViewBindings.findChildViewById(rootView, id);
      if (send == null) {
        break missingId;
      }

      id = R.id.userName;
      TextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      return new ActivityChatBoxBinding((RelativeLayout) rootView, backArrow, charBoxRecyclerView,
          chatBoxToolbar, enterMsg, imageView4, imageView5, imageView6, linear, profileImage, send,
          userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
