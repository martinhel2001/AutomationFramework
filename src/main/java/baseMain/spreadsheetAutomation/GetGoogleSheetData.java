package baseMain.spreadsheetAutomation;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR
// NO USARRRRRRRRRRRRRRRRRRRRRR



public class GetGoogleSheetData {
    private static final String APPLICATION_NAME = "oeautomationgsuite";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static Sheets sheetService;

    /**
     * Global instance of the scopes required by this quickstart. If modifying these
     * scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GetGoogleSheetData.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("martin.tellechea");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
    public static List<List<Object>> getData(String range) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E";
        //final String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();
        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            return null;
        } else {
            return values;
        }
    }

    public static Object[][] getTableArray(String range) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1qwkasfqV0uq5rln0KLTmqw1_jw9p0-z3I41NpXk_ykw";

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();
        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();

        // List of Lists -> Object [][]  ** Metodo original, funciona OK
        /*
        String[][] tabArray = null;
        int startRow = 0;
        int startCol = 0;
        int ci,cj;
        int totalRows = values.size();
        tabArray=new String[totalRows][4];
        ci=0;
        System.out.println("Tamaño de VAlues: "+values.size());

        for (int i=startRow;i<totalRows;i++, ci++) {
            cj = 0;
            for (int j = startCol; j <= 3; j++, cj++) {
                tabArray[ci][cj] = values.get(i).get(j).toString();
                System.out.println(tabArray[ci][cj]);

            }
        }

        return tabArray;
         */

        // List of Lists -> Object [][]  ** Metodo provisto por Tifi, funciona OK
        Object[][] data = values.stream()
                .map(list -> list.toArray())
                .toArray(Object[][]::new);

        return data;
    }




    public static List<List<Object>> getAllCurrentPromoInfo() throws IOException, GeneralSecurityException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        List<List<Object>> values = getData("A2:D20");

        values.removeIf(e->(!(ZonedDateTime.now().isAfter(LocalDateTime.parse(e.get(0).toString(), dateTimeFormatter).atZone(ZoneId.of("-04:00"))) && ZonedDateTime.now().isBefore(LocalDateTime.parse(e.get(1).toString(), dateTimeFormatter).atZone(ZoneId.of("-04:00"))))));

        for (List row : values){
            System.out.format("Start: %s, End: %s, Brand: %s, Promo: %s \n", row.get(0).toString(), row.get(1).toString(), row.get(2).toString(), row.get(3).toString());
        }
        return values;
    }
}
