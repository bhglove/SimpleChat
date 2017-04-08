package bhglove.cpsc482.edu.simplechat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bhglove.cpsc482.edu.simplechat.view.ConversationListFragment;
import bhglove.cpsc482.edu.simplechat.view.ProfileFragment;

/**
 * Created by Benjamin on 10/23/16.
 */

public class ConversationPagerAdapter extends FragmentStatePagerAdapter {
    private int mTabSize;

    public ConversationPagerAdapter(FragmentManager fragmentManager, int tabSize){
        super(fragmentManager);
        this.mTabSize = tabSize;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ConversationListFragment.newInstance("Benji");
            case 1:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabSize;
    }
}
