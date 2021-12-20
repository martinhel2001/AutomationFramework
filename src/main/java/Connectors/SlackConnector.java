package Connectors;
import java.io.IOException;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;

import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;

public class SlackConnector {
    Slack slack;
    String token;
    String channel;

    public SlackConnector () {
        slack = Slack.getInstance();
    }

    public void postMessageFailedTC(String token, String channel, String TCname, String errorMessage, String screenshotURL, String mantisID, String ciUrl, String mantisURL) {

        if (screenshotURL=="") screenshotURL="https://mantisautomation.000webhostapp.com/mantis/images/spotbugs_icon_only_zoom_256px.png";

        try {
            String finalScreenshotURL = screenshotURL;
            String blocks = "[\n" +
                    "\t\t{\n" +
                    "\t\t\t\"type\": \"section\",\n" +
                    "\t\t\t\"text\": {\n" +
                    "\t\t\t\t\"type\": \"mrkdwn\",\n" +
                    "\t\t\t\t\"text\": \"Error found during CI run at *"+TCname+"*.\\n\\n Error message: *"+errorMessage+"*\"\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"type\": \"divider\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"type\": \"image\",\n" +
                    "\t\t\t\"image_url\": \""+ finalScreenshotURL +"\",\n" +
                    "\t\t\t\"alt_text\": \"inspiration\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"type\": \"actions\",\n" +
                    "\t\t\t\"elements\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"type\": \"button\",\n" +
                    "\t\t\t\t\t\"text\": {\n" +
                    "\t\t\t\t\t\t\"type\": \"plain_text\",\n" +
                    "\t\t\t\t\t\t\"text\": \"See CI run\",\n" +
                    "\t\t\t\t\t\t\"emoji\": true\n" +
                    "\t\t\t\t\t},\n" +
                    "\t\t\t\t\t\"value\": \"click_me_CIrun\",\n" +
                    "\t\t\t\t\t\"action_id\": \"actionId-0\",\n" +
                    "\t\t\t\t\t\"url\": \""+ciUrl+"\"\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"type\": \"button\",\n" +
                    "\t\t\t\t\t\"text\": {\n" +
                    "\t\t\t\t\t\t\"type\": \"plain_text\",\n" +
                    "\t\t\t\t\t\t\"text\": \"See Bug in Mantis\",\n" +
                    "\t\t\t\t\t\t\"emoji\": true\n" +
                    "\t\t\t\t\t},\n" +
                    "\t\t\t\t\t\"value\": \"click_me_Mantis\",\n" +
                    "\t\t\t\t\t\"action_id\": \"actionId-1\",\n" +
                    "\t\t\t\t\t\"url\": \""+mantisURL+"/view.php?id="+mantisID+"\"\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}\n" +
                    "\t]";

            System.out.println("Block a postear a Slack: "+blocks);

            ChatPostMessageResponse response = slack.methods(token).chatPostMessage
                    (req -> req
                    .channel(channel).blocksAsString(blocks)
                    )
                    //.text("Write one, post anywhere"))
                    ;



            if (response.isOk()) {
                Message postedMessage = response.getMessage();
                System.out.println("Slack message posted succesfully: "+postedMessage);
            } else {
                String errorCode = response.getError(); // e.g., "invalid_auth", "channel_not_found"
                System.out.println("Slack message posted with errors: "+errorCode);
            }
        } catch (SlackApiException requestFailure) {
            // Slack API responded with unsuccessful status code (= not 20x)
            System.out.println("Slack API responded with unsuccesful status code: "+requestFailure.getMessage());
        } catch (IOException connectivityIssue) {
            // Throwing this exception indicates your app or Slack servers had a connectivity issue.
            System.out.println("Slack API responded with connectiviy issues: "+connectivityIssue.getMessage());
        }
    }


    public void postMessageTestRunFinished(String token, String channel, String logURL, String extentReportsURL, String ciUrl) {

        try {
            ChatPostMessageResponse response = slack.methods(token).chatPostMessage
                    (req -> req
                            .channel(channel).blocksAsString("[\n" +
                                    "\t\t{\n" +
                                    "\t\t\t\"type\": \"divider\"\n" +
                                    "\t\t},\n" +
                                    "\t\t{\n" +
                                    "\t\t\t\"type\": \"section\",\n" +
                                    "\t\t\t\"text\": {\n" +
                                    "\t\t\t\t\"type\": \"mrkdwn\",\n" +
                                    "\t\t\t\t\"text\": \"*Suite run FINISHED at * "+extentReportsURL+"\"\n" +
                                    "\t\t\t}\n" +
                                    "\t\t},\n" +
                                    "\t\t{\n" +
                                    "\t\t\t\"type\": \"actions\",\n" +
                                    "\t\t\t\"elements\": [\n" +
                                    "\t\t\t\t{\n" +
                                    "\t\t\t\t\t\"type\": \"button\",\n" +
                                    "\t\t\t\t\t\"text\": {\n" +
                                    "\t\t\t\t\t\t\"type\": \"plain_text\",\n" +
                                    "\t\t\t\t\t\t\"text\": \"See CI run\",\n" +
                                    "\t\t\t\t\t\t\"emoji\": true\n" +
                                    "\t\t\t\t\t},\n" +
                                    "\t\t\t\t\t\"value\": \"click_me_CIrun\",\n" +
                                    "\t\t\t\t\t\"action_id\": \"actionId-0\",\n" +
                                    "\t\t\t\t\t\"url\": \""+ciUrl+"\"\n" +
                                    "\t\t\t\t},\n" +
                                    "\t\t\t\t{\n" +
                                    "\t\t\t\t\t\"type\": \"button\",\n" +
                                    "\t\t\t\t\t\"text\": {\n" +
                                    "\t\t\t\t\t\t\"type\": \"plain_text\",\n" +
                                    "\t\t\t\t\t\t\"text\": \"See Log file\",\n" +
                                    "\t\t\t\t\t\t\"emoji\": true\n" +
                                    "\t\t\t\t\t},\n" +
                                    "\t\t\t\t\t\"value\": \"click_me_Log\",\n" +
                                    "\t\t\t\t\t\"action_id\": \"actionId-1\",\n" +
                                    "\t\t\t\t\t\"url\": \""+logURL+"\"\n" +
                                    "\t\t\t\t},\n" +
                                    "\t\t\t\t{\n" +
                                    "\t\t\t\t\t\"type\": \"button\",\n" +
                                    "\t\t\t\t\t\"text\": {\n" +
                                    "\t\t\t\t\t\t\"type\": \"plain_text\",\n" +
                                    "\t\t\t\t\t\t\"text\": \"See Report\",\n" +
                                    "\t\t\t\t\t\t\"emoji\": true\n" +
                                    "\t\t\t\t\t},\n" +
                                    "\t\t\t\t\t\"value\": \"click_me_Report\",\n" +
                                    "\t\t\t\t\t\"action_id\": \"actionId-2\",\n" +
                                    "\t\t\t\t\t\"url\": \""+extentReportsURL+"\"\n" +
                                    "\t\t\t\t}\n" +
                                    "\t\t\t]\n" +
                                    "\t\t}\n" +
                                    "\t]")
                    )
                    //.text("Write one, post anywhere"))
                    ;



            if (response.isOk()) {
                Message postedMessage = response.getMessage();
                System.out.println("Slack message posted succesfully: "+postedMessage);
            } else {
                String errorCode = response.getError(); // e.g., "invalid_auth", "channel_not_found"
                System.out.println("Slack message posted with errors: "+errorCode);
            }
        } catch (SlackApiException requestFailure) {
            // Slack API responded with unsuccessful status code (= not 20x)
            System.out.println("Slack API responded with unsuccesful status code: "+requestFailure.getMessage());
        } catch (IOException connectivityIssue) {
            // Throwing this exception indicates your app or Slack servers had a connectivity issue.
            System.out.println("Slack API responded with connectiviy issues: "+connectivityIssue.getMessage());
        }
    }
}
