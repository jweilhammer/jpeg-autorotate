/**
 * MIT License
 *
 * Copyright (c) 2019 Domenic Seccareccia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * More information about this project is available at:
 *     https://github.com/domsec/jpeg-autorotate
 */

package com.domsec.imaging;

import com.domsec.JpegAutorotateException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

final class JpegImageReader {

    /**
     * Not intended for instantiation.
     */
    private JpegImageReader() {
        throw new IllegalStateException("Not intended for instantiation.");
    }

    /**
     * Attempts to read JPEG file to a BufferedImage.
     *
     * @param bytes
     *              {@code bytes} containing a JPEG image file.
     * @return If successful, a {@code BufferedImage} containing image data.
     * @throws JpegAutorotateException
     *              In the event the {@code bytes} is unable to be read.
     */
    protected static BufferedImage readImage(final byte[] bytes) throws JpegAutorotateException {
        try {
            File tempFile = File.createTempFile("tmp", "jpg");
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(bytes);

            BufferedImage image = ImageIO.read(tempFile);

            fos.flush();
            fos.close();
            tempFile.deleteOnExit();

            return image;
        } catch (IOException e) {
            throw new JpegAutorotateException("Unable to read JPEG image.", e);
        }
    }

}