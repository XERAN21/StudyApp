package com.ss.studysystem.UI.utils;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class png_metadata_handler {

    /**
     * @type FILE
     * @param inputFile
     * @param outputFile
     * @param zoomLevel
     * @param offsetX
     * @param offsetY
     * @throws Exception
     */

    public static void write_png_metadata(File inputFile, File outputFile, String zoomLevel, String offsetX, String offsetY) throws Exception {
        BufferedImage img = ImageIO.read(inputFile);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

        ImageWriteParam writeParam = writer.getDefaultWriteParam();
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);

        IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);

        IIOMetadataNode textEntry1 = create_meta_entry("zoom_level", zoomLevel);
        IIOMetadataNode textEntry2 = create_meta_entry("offset_x", offsetX);
        IIOMetadataNode textEntry3 = create_meta_entry("offset_y", offsetY);

        IIOMetadataNode text = new IIOMetadataNode("tEXt");
        text.appendChild(textEntry1);
        text.appendChild(textEntry2);
        text.appendChild(textEntry3);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_png_1.0");
        root.appendChild(text);

        metadata.mergeTree("javax_imageio_png_1.0", root);

        ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
        writer.setOutput(ios);
        writer.write(metadata, new IIOImage(img, null, metadata), writeParam);
        ios.close();
        writer.dispose();

        System.out.println("Metadata written to file: " + outputFile.getAbsolutePath());
    }


    public static String read_png_metadata(File imageFile, String key) throws IOException {
        ImageReader reader = ImageIO.getImageReadersByFormatName("png").next();
        reader.setInput(ImageIO.createImageInputStream(imageFile), true);

        IIOMetadata metadata = reader.getImageMetadata(0);

        Node root = metadata.getAsTree("javax_imageio_png_1.0");

        NodeList textNodes = root.getChildNodes();
        for (int i = 0; i < textNodes.getLength(); i++) {
            Node textNode = textNodes.item(i);
            if ("tEXt".equals(textNode.getNodeName())) {
                NodeList entries = textNode.getChildNodes();
                for (int j = 0; j < entries.getLength(); j++) {
                    Node entry = entries.item(j);
                    if ("tEXtEntry".equals(entry.getNodeName())) {
                        String keyword = entry.getAttributes().getNamedItem("keyword").getNodeValue();
                        String value = entry.getAttributes().getNamedItem("value").getNodeValue();
                        if (key.equals(keyword)) {
                            return value;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @type BYTE
     * @param buffImg
     * @param zoomLevel
     * @param offsetX
     * @param offsetY
     * @return
     * @throws Exception
     */
    public static byte[] write_byte_metadata(BufferedImage buffImg, String zoomLevel, String offsetX, String offsetY) throws Exception {
        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();

        ImageWriteParam writeParam = writer.getDefaultWriteParam();
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);

        IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);

        IIOMetadataNode textEntry1 = create_meta_entry("zoom_level", zoomLevel);
        IIOMetadataNode textEntry2 = create_meta_entry("offset_x", offsetX);
        IIOMetadataNode textEntry3 = create_meta_entry("offset_y", offsetY);

        IIOMetadataNode text = new IIOMetadataNode("tEXt");
        text.appendChild(textEntry1);
        text.appendChild(textEntry2);
        text.appendChild(textEntry3);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_png_1.0");
        root.appendChild(text);

        metadata.mergeTree("javax_imageio_png_1.0", root);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream stream = ImageIO.createImageOutputStream(baos);
        writer.setOutput(stream);
        writer.write(metadata, new IIOImage(buffImg, null, metadata), writeParam);
        stream.close();

        return baos.toByteArray();
    }


    public static String read_byte_metadata(byte[] imageData, String key) throws IOException {
        ImageReader reader = ImageIO.getImageReadersByFormatName("png").next();
        reader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(imageData)), true);

        IIOMetadata metadata = reader.getImageMetadata(0);

        Node root = metadata.getAsTree("javax_imageio_png_1.0");

        NodeList textNodes = root.getChildNodes();
        for (int i = 0; i < textNodes.getLength(); i++) {
            Node textNode = textNodes.item(i);
            if ("tEXt".equals(textNode.getNodeName())) {
                NodeList entries = textNode.getChildNodes();
                for (int j = 0; j < entries.getLength(); j++) {
                    Node entry = entries.item(j);
                    if ("tEXtEntry".equals(entry.getNodeName())) {
                        String keyword = entry.getAttributes().getNamedItem("keyword").getNodeValue();
                        String value = entry.getAttributes().getNamedItem("value").getNodeValue();
                        if (key.equals(keyword)) {
                            return value;
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     *
     * @param key
     * @param value
     * @return
     */
    private static IIOMetadataNode create_meta_entry(String key, String value) {
        IIOMetadataNode textEntry = new IIOMetadataNode("tEXtEntry");
        textEntry.setAttribute("keyword", key);
        textEntry.setAttribute("value", value);
        return textEntry;
    }

    /**
     *
     * @TYPE database
     */

    public static void write_png_metadata_to_db(Blob blob, String zoomLevel, String offsetX, String offsetY) throws Exception {
        byte[] imgBytes = blob.getBytes(1, (int) blob.length());
        ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
        BufferedImage img = ImageIO.read(bis);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        ImageWriteParam writeParam = writer.getDefaultWriteParam();
        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
        IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);

        IIOMetadataNode textEntry1 = create_meta_entry("zoom_level", zoomLevel);
        IIOMetadataNode textEntry2 = create_meta_entry("offset_x", offsetX);
        IIOMetadataNode textEntry3 = create_meta_entry("offset_y", offsetY);

        IIOMetadataNode text = new IIOMetadataNode("tEXt");
        text.appendChild(textEntry1);
        text.appendChild(textEntry2);
        text.appendChild(textEntry3);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_png_1.0");
        root.appendChild(text);
        metadata.mergeTree("javax_imageio_png_1.0", root);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(byteArrayOutputStream);
        writer.setOutput(ios);
        writer.write(metadata, new IIOImage(img, null, metadata), writeParam);
        ios.close();
        writer.dispose();

        byte[] imageWithMetadata = byteArrayOutputStream.toByteArray();

//        String sql = "INSERT INTO your_table (image_column) VALUES (?)";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            Blob blobToSave = new SerialBlob(imageWithMetadata);
//            ps.setBlob(1, blobToSave);
//            ps.executeUpdate();
//            System.out.println("Image with metadata saved to database.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws Exception {
        File inputfFile = new File("/Users/thantzinlin/Downloads/Gladiia Arknights by Chuzenji.png");

        File outputFile = new File("/Users/thantzinlin/Downloads/outout.png");

        double zoom = 2.71;
        double new_ = zoom * 0.85;
        write_png_metadata(inputfFile, outputFile, String.valueOf(new_), "547", "271");

        try {
            File inputFile = new File("/Users/thantzinlin/Downloads/outout.png");
            String key = "zoom_level"; // Example key
            String value = read_png_metadata(inputFile, key);
            System.out.println("Value for key '" + key + "': " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
