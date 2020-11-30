package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    private RecyclerView fishrecycler;
    private RecyclerView plantrecycler;
    private RecyclerView filterrecycler;
    private RecyclerView tankrecycler;
    private RecyclerView otherrecycler;

    //Firebase
    private DatabaseReference mFishDatabase;
    private DatabaseReference mPlantsDatabase;
    private DatabaseReference mFilterDatabase;
    private DatabaseReference mTankDatabase;
    private DatabaseReference mOtherDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate (R.layout.fragment_home, container, false);

        mFishDatabase= FirebaseDatabase.getInstance ().getReference ().child ("mFishDatabase");
        mPlantsDatabase=FirebaseDatabase.getInstance ().getReference ().child ("mPlantsDatabase");
        mFilterDatabase=FirebaseDatabase.getInstance ().getReference ().child ("mFilterDatabase");
        mTankDatabase=FirebaseDatabase.getInstance ().getReference ().child ("mTankDatabase");
        mOtherDatabase=FirebaseDatabase.getInstance ().getReference ().child ("mOtherDatabase");

        //1st Recycler
        fishrecycler = myview.findViewById (R.id.recycler_fish);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getActivity (), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout (true);
        layoutManager.setStackFromEnd (true);
        fishrecycler.setHasFixedSize(true);
        fishrecycler.setLayoutManager (layoutManager);

        //2nd Recycler
        plantrecycler=myview.findViewById (R.id.recycler_plants);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager (getActivity (), LinearLayoutManager.HORIZONTAL, false);
        layoutManager1.setReverseLayout (true);
        layoutManager1.setStackFromEnd (true);
        plantrecycler.setHasFixedSize (true);
        plantrecycler.setLayoutManager (layoutManager1);

        //3rd Recycler
        filterrecycler=myview.findViewById (R.id.recycler_filter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager (getActivity (), LinearLayoutManager.HORIZONTAL, false);
        layoutManager2.setReverseLayout (true);
        layoutManager2.setStackFromEnd (true);
        filterrecycler.setHasFixedSize (true);
        filterrecycler.setLayoutManager (layoutManager2);

        //4th Recycler
        tankrecycler=myview.findViewById (R.id.recycler_tank);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager (getActivity (), LinearLayoutManager.HORIZONTAL, false);
        layoutManager3.setReverseLayout (true);
        layoutManager3.setStackFromEnd (true);
        tankrecycler.setHasFixedSize (true);
        tankrecycler.setLayoutManager (layoutManager3);

        //5th Recycler
        otherrecycler=myview.findViewById (R.id.recycler_other);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager (getActivity (), LinearLayoutManager.HORIZONTAL, false);
        layoutManager4.setReverseLayout (true);
        layoutManager4.setStackFromEnd (true);
        otherrecycler.setHasFixedSize (true);
        otherrecycler.setLayoutManager (layoutManager4);

        return myview;
    }

    @Override
    public void onStart() {
        super.onStart ();

        //mFishDatabase
        FirebaseRecyclerAdapter<Data,HomeViewHolder>adapterOne=new FirebaseRecyclerAdapter<Data, HomeViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        HomeViewHolder.class,
                        mFishDatabase

                ) {
            @Override
            protected void populateViewHolder(HomeViewHolder homeViewHolder, final Data model, int position) {
                homeViewHolder.msetTitle (model.getTitle ());
                homeViewHolder.msetDescription (model.getDescription ());
                homeViewHolder.msetImage (model.getImage ());
                homeViewHolder.msetPrice (model.getPrice ());

                homeViewHolder.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getActivity (),DetailActivity.class);
                        intent.putExtra ("title",model.getTitle ());
                        intent.putExtra ("description",model.getDescription ());
                        intent.putExtra ("image",model.getImage ());
                        intent.putExtra ("price",model.getPrice ());
                        startActivity (intent);
                    }
                });

            }
        };
        fishrecycler.setAdapter (adapterOne);

        //mPlantsDatabase
        FirebaseRecyclerAdapter<Data,HomeViewHolder1>adapterTwo=new FirebaseRecyclerAdapter<Data, HomeViewHolder1>
                (
                        Data.class,
                        R.layout.item_data,
                        HomeViewHolder1.class,
                        mPlantsDatabase
                ) {
            @Override
            protected void populateViewHolder(HomeViewHolder1 homeViewHolder1, final Data model, int position) {

                homeViewHolder1.msetTitle (model.getTitle ());
                homeViewHolder1.msetDescription (model.getDescription ());
                homeViewHolder1.msetImage (model.getImage ());
                homeViewHolder1.msetPrice (model.getPrice ());

                homeViewHolder1.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getActivity (),DetailActivity.class);
                        intent.putExtra ("title",model.getTitle ());
                        intent.putExtra ("description",model.getDescription ());
                        intent.putExtra ("image",model.getImage ());
                        intent.putExtra ("price",model.getPrice ());
                        startActivity (intent);
                    }
                });
            }
        };
        plantrecycler.setAdapter (adapterTwo);

        //mFilterDatabase
        FirebaseRecyclerAdapter<Data,HomeViewHolder2>adapterThree=new FirebaseRecyclerAdapter<Data, HomeViewHolder2>
                (
                        Data.class,
                        R.layout.item_data,
                        HomeViewHolder2.class,
                        mFilterDatabase
                ) {
            @Override
            protected void populateViewHolder(HomeViewHolder2 homeViewHolder2, final Data model, int position) {

                homeViewHolder2.msetTitle (model.getTitle ());
                homeViewHolder2.msetDescription (model.getDescription ());
                homeViewHolder2.msetImage (model.getImage ());
                homeViewHolder2.msetPrice (model.getPrice ());

                homeViewHolder2.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getActivity (),DetailActivity.class);
                        intent.putExtra ("title",model.getTitle ());
                        intent.putExtra ("description",model.getDescription ());
                        intent.putExtra ("image",model.getImage ());
                        intent.putExtra ("price",model.getPrice ());
                        startActivity (intent);
                    }
                });
            }
        };
        filterrecycler.setAdapter (adapterThree);

        //mTankDatabase
        FirebaseRecyclerAdapter<Data,HomeViewHolder3>adapterFour=new FirebaseRecyclerAdapter<Data, HomeViewHolder3>
                (
                        Data.class,
                        R.layout.item_data,
                        HomeViewHolder3.class,
                        mTankDatabase
                ) {
            @Override
            protected void populateViewHolder(HomeViewHolder3 homeViewHolder3, final Data model, int position) {

                homeViewHolder3.msetTitle (model.getTitle ());
                homeViewHolder3.msetDescription (model.getDescription ());
                homeViewHolder3.msetImage (model.getImage ());
                homeViewHolder3.msetPrice (model.getPrice ());

                homeViewHolder3.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getActivity (),DetailActivity.class);
                        intent.putExtra ("title",model.getTitle ());
                        intent.putExtra ("description",model.getDescription ());
                        intent.putExtra ("image",model.getImage ());
                        intent.putExtra ("price",model.getPrice ());
                        startActivity (intent);
                    }
                });
            }
        };
        tankrecycler.setAdapter (adapterFour);

        //mOtherDatabase
        FirebaseRecyclerAdapter<Data,HomeViewHolder4>adapterFive=new FirebaseRecyclerAdapter<Data, HomeViewHolder4>
                (
                        Data.class,
                        R.layout.item_data,
                        HomeViewHolder4.class,
                        mOtherDatabase
                ) {
            @Override
            protected void populateViewHolder(HomeViewHolder4 homeViewHolder4, final Data model, int position) {

                homeViewHolder4.msetTitle (model.getTitle ());
                homeViewHolder4.msetDescription (model.getDescription ());
                homeViewHolder4.msetImage (model.getImage ());
                homeViewHolder4.msetPrice (model.getPrice ());

                homeViewHolder4.myview.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent (getActivity (),DetailActivity.class);
                        intent.putExtra ("title",model.getTitle ());
                        intent.putExtra ("description",model.getDescription ());
                        intent.putExtra ("image",model.getImage ());
                        intent.putExtra ("price",model.getPrice ());
                        startActivity (intent);
                    }
                });
            }
        };
        otherrecycler.setAdapter (adapterFive);

    }

    //Fish
    public static class HomeViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public HomeViewHolder(@NonNull View itemView) {
            super (itemView);
            myview=itemView;
        }
        public void msetTitle(String title){
            TextView mTitle=myview.findViewById (R.id.title);
            mTitle.setText (title);
        }
        public void msetDescription(String description){
            TextView mDescription=myview.findViewById (R.id.description);
            mDescription.setText (description);
        }
        public void msetImage(final String image){
            final ImageView mImage=myview.findViewById (R.id.imageView);

            Picasso.get ().load (image).networkPolicy (NetworkPolicy.OFFLINE).into (mImage,new Callback () {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get ().load (image).into (mImage);
                }
            });
        }
        public void msetPrice(String price) {
            TextView mPrice = myview.findViewById (R.id.price);
            mPrice.setText (price);
        }
    }

    //Plants
    public static class HomeViewHolder1 extends RecyclerView.ViewHolder {

        View myview;

        public HomeViewHolder1(@NonNull View itemView) {
            super (itemView);
            myview = itemView;
        }

        public void msetTitle(String title) {
            TextView mTitle = myview.findViewById (R.id.title);
            mTitle.setText (title);
        }

        public void msetDescription(String description) {
            TextView mDescription = myview.findViewById (R.id.description);
            mDescription.setText (description);
        }

        public void msetImage(final String image) {
            final ImageView mImage = myview.findViewById (R.id.imageView);

            Picasso.get ().load (image).networkPolicy (NetworkPolicy.OFFLINE).into (mImage, new Callback () {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get ().load (image).into (mImage);
                }
            });
        }
        public void msetPrice(String price) {
            TextView mPrice = myview.findViewById (R.id.price);
            mPrice.setText (price);
        }
    }

    //Filter
    public static class HomeViewHolder2 extends RecyclerView.ViewHolder{

        View myview;
        public HomeViewHolder2(@NonNull View itemView) {
            super (itemView);
            myview=itemView;
        }
        public void msetTitle(String title) {
            TextView mTitle = myview.findViewById (R.id.title);
            mTitle.setText (title);
        }

        public void msetDescription(String description) {
            TextView mDescription = myview.findViewById (R.id.description);
            mDescription.setText (description);
        }

        public void msetImage(final String image) {
            final ImageView mImage = myview.findViewById (R.id.imageView);

            Picasso.get ().load (image).networkPolicy (NetworkPolicy.OFFLINE).into (mImage, new Callback () {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get ().load (image).into (mImage);
                }
            });
        }
        public void msetPrice(String price) {
            TextView mPrice = myview.findViewById (R.id.price);
            mPrice.setText (price);
        }
    }

    //Tank
    public static class HomeViewHolder3 extends RecyclerView.ViewHolder{

        View myview;
        public HomeViewHolder3(@NonNull View itemView) {
            super (itemView);
            myview=itemView;
        }
        public void msetTitle(String title) {
            TextView mTitle = myview.findViewById (R.id.title);
            mTitle.setText (title);
        }

        public void msetDescription(String description) {
            TextView mDescription = myview.findViewById (R.id.description);
            mDescription.setText (description);
        }

        public void msetImage(final String image) {
            final ImageView mImage = myview.findViewById (R.id.imageView);

            Picasso.get ().load (image).networkPolicy (NetworkPolicy.OFFLINE).into (mImage, new Callback () {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get ().load (image).into (mImage);
                }
            });
        }
        public void msetPrice(String price) {
            TextView mPrice = myview.findViewById (R.id.price);
            mPrice.setText (price);
        }
    }

    //Other
    public static class HomeViewHolder4 extends RecyclerView.ViewHolder{

        View myview;
        public HomeViewHolder4(@NonNull View itemView) {
            super (itemView);
            myview=itemView;
        }
        public void msetTitle(String title) {
            TextView mTitle = myview.findViewById (R.id.title);
            mTitle.setText (title);
        }

        public void msetDescription(String description) {
            TextView mDescription = myview.findViewById (R.id.description);
            mDescription.setText (description);
        }

        public void msetImage(final String image) {
            final ImageView mImage = myview.findViewById (R.id.imageView);

            Picasso.get ().load (image).networkPolicy (NetworkPolicy.OFFLINE).into (mImage, new Callback () {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get ().load (image).into (mImage);
                }
            });
        }
        public void msetPrice(String price) {
            TextView mPrice = myview.findViewById (R.id.price);
            mPrice.setText (price);
        }
    }
}