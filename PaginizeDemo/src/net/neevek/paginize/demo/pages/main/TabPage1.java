package net.neevek.paginize.demo.pages.main;

import android.view.View;
import android.widget.Button;
import net.neevek.lib.android.paginize.InnerPage;
import net.neevek.lib.android.paginize.PageActivity;
import net.neevek.lib.android.paginize.annotation.InjectView;
import net.neevek.lib.android.paginize.annotation.PageLayout;
import net.neevek.paginize.demo.*;
import net.neevek.paginize.demo.pages.other.*;

/**
 * Created by neevek on 3/16/14.
 */
@PageLayout(R.layout.inner_page_tab1)
public class TabPage1 extends InnerPage implements View.OnClickListener {

    @InjectView(value = R.id.btn_next_page, listeners = {View.OnClickListener.class})
    private Button mBtnNextPage;
    @InjectView(value = R.id.btn_list_page, listeners = {View.OnClickListener.class})
    private Button mBtnListPage;
    @InjectView(value = R.id.btn_show_alert, listeners = {View.OnClickListener.class})
    private Button mBtnShowAnAlertPage;

    public TabPage1(PageActivity context) {
        super(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_page:
                new TestPage(mContext).show(null, true);
                break;
            case R.id.btn_list_page:
                new ListPage(mContext).show(null, true);
                break;
            case R.id.btn_show_alert:
                new AlertPage(mContext).show();
                break;
        }
    }
}