package com.robertoallende.contacts.jobs;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

public abstract class ContactsAppJob extends Job {

    public static int LOW = 0;
    public static int MID = 500;
    public static int HIGH = 1000;

    public ContactsAppJob() {
        super(new Params(HIGH).requireNetwork().groupBy("ContactsApplicationJob").singleInstanceBy("DiagnosisApplicationJob"));
    }

    @Override
    public void onAdded() {

    }


    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }


}
