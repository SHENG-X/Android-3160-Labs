/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
public class GreenAdapter extends  RecyclerView.Adapter<GreenAdapter.NumberViewHolder>{

    // TODO (G1) Add a private int variable called mNumberItems
    private int mNumberItems;
    // TODO (G2) Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    public GreenAdapter(int numberOfItems){
        mNumberItems=numberOfItems;
    }
    // TODO (G3) Store the numberOfItems parameter in mNumberItems

    // TODO (G5) Override the onCreateViewHolder method
    // TODO (G6) Create and return a new NumberViewHolder within this method

    // TODO (G7) Override onBindViewHolder
    // TODO (G8) Within onBindViewHolder, call holder.bind and pass in the position

    // TODO (G9) Override getItemCount and return the number of items to display

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View thisItemView=inflater.inflate(R.layout.number_list_item,parent,false);
        return new NumberViewHolder(thisItemView);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }



    private static final String TAG = GreenAdapter.class.getSimpleName();


    // TODO (G10) Create a class called NumberViewHolder that extends RecyclerView.ViewHolder
    // TODO (G11) Create a constructor for NumberViewHolder that accepts a View called itemView as a parameter
    // TODO (G12) Within NumberViewHolder, create a TextView variable called listItemNumberView
    // TODO (G13) Within the constructor, call super(itemView) and then find listItemNumberView by ID
    // TODO (G14) Within the NumberViewHolder class, create a void method called bind that accepts an int parameter called listIndex
    // TODO (G15) Within bind, set the text of listItemNumberView to the listIndex
    // TODO (G16) Be careful to get the String representation of listIndex, as using setText with an int does something different

    class NumberViewHolder extends RecyclerView.ViewHolder{
        TextView listNumberView;
        public NumberViewHolder(final View itemView) {
            super(itemView);
            listNumberView=(TextView) itemView.findViewById(R.id.tv_item_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),getAdapterPosition()+"",Toast.LENGTH_LONG).show();

                }
            });
        }
        public void bind(int index){
            listNumberView.setText(""+index);
        }
    }

}