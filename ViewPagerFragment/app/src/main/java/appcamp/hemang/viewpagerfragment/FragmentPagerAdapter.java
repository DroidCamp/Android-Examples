package appcamp.hemang.viewpagerfragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Hemang on 17/08/16.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    ArrayList<String> tabs;
    Context ctx;

    public FragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabs = new ArrayList<String>();
        tabs.add("ABOUT");
        tabs.add("SPONSORS");
        tabs.add("TEAM");
        tabs.add("CHECK");
        this.ctx = context;

    }

    @Override
    public Fragment getItem(int position) {
        return FragmentData.newInstance("", "");
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
