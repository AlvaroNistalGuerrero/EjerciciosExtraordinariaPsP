package unidad3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ConexionConURLHTTP {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java ImageDownloader <URL>");
            return;
        }

        String url = args[0];
        try {
            downloadImages(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadImages(String url) throws IOException {
        String htmlContent = fetchHtmlContent(url);
        List<String> imageUrls = extractImageUrls(htmlContent, url);

        for (String src : imageUrls) {
            downloadImage(src);
        }
    }

    public static String fetchHtmlContent(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                content.append(System.lineSeparator());
            }
        }

        return content.toString();
    }

    public static List<String> extractImageUrls(String htmlContent, String baseUrl) {
        List<String> imageUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img[^>]+src=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String src = matcher.group(1);
            if (!src.startsWith("http")) {
                src = resolveRelativeUrl(baseUrl, src);
            }
            imageUrls.add(src);
        }

        return imageUrls;
    }

    public static String resolveRelativeUrl(String baseUrl, String relativeUrl) {
        try {
            URL base = new URL(baseUrl);
            URL resolved = new URL(base, relativeUrl);
            return resolved.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return relativeUrl;
        }
    }

    public static void downloadImage(String src) {
        try {
            URL imageUrl = new URL(src);
            BufferedImage img = ImageIO.read(imageUrl);

            if (img != null) {
                String fileName = "image_" + UUID.randomUUID() + ".jpg";
                File outputfile = new File(fileName);
                ImageIO.write(img, "jpg", outputfile);
                System.out.println("Imagen descargada: " + fileName);
            } else {
                System.err.println("No se pudo descargar la imagen: " + src);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
