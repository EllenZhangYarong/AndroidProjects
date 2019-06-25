package com.ellen.taskninefragment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskninefragment.R;
import com.ellen.taskninefragment.adapters.ActorAdapter;
import com.ellen.taskninefragment.models.ActorsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment represents the back of card
 * Created by ellen on 15/12/14.
 */
public class FragmentBack extends android.app.Fragment {
    public FragmentBack() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_fragment_back, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.actorCards);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ActorAdapter actorAdapter = new ActorAdapter(createList(6));
        recyclerView.setAdapter(actorAdapter);


        return root;
    }

    private List<ActorsModel> createList(int size) {
        int[] imgs = new int[]{R.drawable.freddy, R.drawable.studard,
                R.drawable.ash, R.drawable.violet,
                R.drawable.meison, R.drawable.pinolope};

        List<ActorsModel> result = new ArrayList<ActorsModel>();

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setImgUri(imgs[0]);
        actorsModel.setName("弗雷迪");
        actorsModel.setActorName("演员 伊恩·麦凯伦");
        actorsModel.setAbriefIntro("一个一生多演龙套角色的演员,傲娇毒舌,热衷于表现自己弗雷迪虽然睿智又高傲，却总像小学男生般用刻薄毒舌来表达情比金坚。");
        result.add(actorsModel);

        ActorsModel actorsModel1 = new ActorsModel();
        actorsModel.setImgUri(imgs[1]);
        actorsModel.setName("斯图尔特");
        actorsModel.setActorName("演员 德里克·雅各比");
        actorsModel.setAbriefIntro("退休的酒吧台经理,与弗雷迪相恋48年但母亲并不知情斯图尔特善良又心软。");
        result.add(actorsModel1);

        ActorsModel actorsModel2 = new ActorsModel();
        actorsModel.setImgUri(imgs[2]);
        actorsModel.setName("艾什");
        actorsModel.setActorName("演员 伊万·瑞恩");
        actorsModel.setAbriefIntro("二人的新邻居,(暂时是)直男的英俊小伙子。");
        result.add(actorsModel2);

        ActorsModel actorsModel3 = new ActorsModel();
        actorsModel.setImgUri(imgs[3]);
        actorsModel.setName("维奥莱特");
        actorsModel.setActorName("演员 弗朗西斯·德·拉·图瓦");
        actorsModel.setAbriefIntro("二人的亲密朋友,终身未婚,热衷于泡帅哥和调戏艾什。");
        result.add(actorsModel3);

        ActorsModel actorsModel4 = new ActorsModel();
        actorsModel.setImgUri(imgs[4]);
        actorsModel.setName("梅森");
        actorsModel.setActorName("演员 菲利普-沃斯");
        actorsModel.setAbriefIntro("二人的老朋友,常年被忽视,毒舌功力强。");
        result.add(actorsModel4);

        ActorsModel actorsModel5 = new ActorsModel();
        actorsModel.setImgUri(imgs[5]);
        actorsModel.setName("佩内洛普");
        actorsModel.setActorName("演员 玛西娅·沃伦");
        actorsModel.setAbriefIntro("二人的老朋友,注意力短暂,爱走神,补刀之王。");
        result.add(actorsModel5);


        return result;
    }
}
