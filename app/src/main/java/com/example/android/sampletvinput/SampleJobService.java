/*
 * Copyright 2016 The Android Open Source Project
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
package com.example.android.sampletvinput;

import android.net.Uri;

import com.example.android.sampletvinput.JsonParser.Parser;
import com.google.android.media.tv.companionlibrary.EpgSyncJobService;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.Program;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EpgSyncJobService that periodically runs to update channels and programs.
 */
public class SampleJobService extends EpgSyncJobService {

    private Parser parser;

    @Override
    public List<Channel> getChannels() {
//      Add channels through an XMLTV file
//        List<Channel> channelList;
 //       XmlTvParser.TvListing listings = RichFeedUtil.getRichTvListings(this);
 //       channelList = new ArrayList<>(listings.getChannels());
//        Log.d("CHANNELLIST", channelList.toString());
 //       Log.d("CHANNELLIST", "--------------------------------------------------------------");

        if(parser == null)
            parser = new Parser();
        List<Channel> channelList = null;
        try {
            channelList = new ArrayList<>(parser.getChannels());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

//      Log.d("CHANNELLISTXML", channelList.toString());
//      Log.d("CHANNELLISTCODE", channelList2.toString());

        return channelList;
    }

    @Override
    public List<Program> getProgramsForChannel(Uri channelUri, Channel channel, long startMs,
                                               long endMs) {
        List<Program> programsList = null;

//        //Is an XMLTV Channel
//        XmlTvParser.TvListing listings = RichFeedUtil.getRichTvListings(getApplicationContext());
//        return listings.getPrograms(channel);

        if(parser == null)
            parser = new Parser();
        try {
            programsList = new ArrayList<>(parser.getPrograms(channel));
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return programsList;

    }
}
