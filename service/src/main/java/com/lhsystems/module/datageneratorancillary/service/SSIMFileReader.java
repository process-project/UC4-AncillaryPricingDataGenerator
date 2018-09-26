package com.lhsystems.module.datageneratorancillary.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Read lines from ssim file.
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
class SSIMFileReader {

    /**
     *
     * Reads the ssim file and filter wrong lines.
     *
     * @param ssimFilePath
     *      path to ssimFile
     * @return
     *      list of proper ssmim file lines
     */
    public List<String> getSsimFileLines(final String ssimFilePath){
        final InputStream in = getClass().getResourceAsStream(ssimFilePath);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        return reader
                .lines()
                .filter(this::isLineStartedWithProperNumber)
                .collect(Collectors.toList());
    }

    /**
     *
     * Check that line is proper to get data from it.
     *
     * @param line
     *        line from input file
     * @return
     *        true if line is proper line
     */
    private boolean isLineStartedWithProperNumber(final String line) {
        return !line.matches("([0124]).*$");
    }

}
