import groovy.util.logging.Log

@Log
class PoC {

    PoC(File file) {
        log.warning "$this should be loading $file"
    }

    double[] getSamples() {
        log.warning "$this should be decoding the samples!"
    }

}
