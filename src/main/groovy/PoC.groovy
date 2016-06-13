import groovy.util.logging.Log

@Log
class PoC {
    def samples

    PoC(File file) {
        log.warning "$this should be loading $file"
        this.samples = file
    }

    double[] getSamples() {
        log.warning "$this should be decoding the samples!"

    }

}
