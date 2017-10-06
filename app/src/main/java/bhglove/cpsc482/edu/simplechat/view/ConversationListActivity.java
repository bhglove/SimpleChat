package bhglove.cpsc482.edu.simplechat.view;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import bhglove.cpsc482.edu.simplechat.R;
import bhglove.cpsc482.edu.simplechat.adapter.ConversationPagerAdapter;
import bhglove.cpsc482.edu.simplechat.android.SharedPreferences;
import bhglove.cpsc482.edu.simplechat.model.User;
import bhglove.cpsc482.edu.simplechat.service.MessagingService;

public class ConversationListActivity extends AppCompatActivity
implements ConversationListFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
        User user = SharedPreferences.getDefaultUser(ConversationListActivity.this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab().setText("Conversations"));
            tabLayout.addTab(tabLayout.newTab().setText("Profile"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final ConversationPagerAdapter pagerAdapter = new ConversationPagerAdapter(getSupportFragmentManager(),
                    tabLayout.getTabCount());
            viewPager.setAdapter(pagerAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
        @Override
        public void onFragmentInteraction(Uri uri){
            Toast.makeText(getApplicationContext(), "Hey", Toast.LENGTH_SHORT).show();
        }

}
