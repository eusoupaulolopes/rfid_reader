package api.reader.speedway.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

public class TagReportListenerImp implements TagReportListener {
	private StringBuilder reportBuilder = new StringBuilder();
	
    @Override
    public void onTagReported(ImpinjReader reader, TagReport report) {
        List<Tag> tags = report.getTags();
         // Cleanning StringBuilder
        for (Tag t : tags) {
        	reportBuilder.setLength(0); 
            reportBuilder.append(" EPC: " + t.getEpc().toHexString().toString().replaceAll("\\s+"," "));

            if (!reader.getName().isEmpty()) {
            	reportBuilder.append(" Reader_name: " + reader.getName());
            } else {
            	reportBuilder.append(" Reader_ip: " + reader.getAddress());
            }

            if (t.isAntennaPortNumberPresent()) {
            	reportBuilder.append(" antenna: " + t.getAntennaPortNumber());
            }

            if (t.isFirstSeenTimePresent()) {
            	reportBuilder.append(" first: " + t.getFirstSeenTime().ToString());
            }

            if (t.isLastSeenTimePresent()) {
            	reportBuilder.append(" last: " + t.getLastSeenTime().ToString());
            }

            if (t.isSeenCountPresent()) {
            	reportBuilder.append(" count: " + t.getTagSeenCount());
            }

            if (t.isRfDopplerFrequencyPresent()) {
            	reportBuilder.append(" doppler: " + t.getRfDopplerFrequency());
            }

            if (t.isPeakRssiInDbmPresent()) {
            	reportBuilder.append(" peak_rssi: " + t.getPeakRssiInDbm());
            }

            if (t.isChannelInMhzPresent()) {
            	reportBuilder.append(" chan_MHz: " + t.getChannelInMhz());
            }

            if (t.isFastIdPresent()) {
            	reportBuilder.append("\n     fast_id: " + t.getTid().toHexString());

            	reportBuilder.append(" model: " +
                        t.getModelDetails().getModelName());

            	reportBuilder.append(" epcsize: " +
                        t.getModelDetails().getEpcSizeBits());

            	reportBuilder.append(" usermemsize: " +
                        t.getModelDetails().getUserMemorySizeBits());
            }

            SpeedwayCaptureTagRepresentation.setJsonTagUnique(reportBuilder.toString());
            SpeedwayCaptureTagRepresentation.showTagUnique();
        }
        //
    }

}
