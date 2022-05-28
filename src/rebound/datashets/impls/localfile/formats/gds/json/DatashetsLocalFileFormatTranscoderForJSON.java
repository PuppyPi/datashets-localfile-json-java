package rebound.datashets.impls.localfile.formats.gds.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import rebound.dataformats.json.JSONUtilities;
import rebound.datashets.api.model.DatashetsTable;
import rebound.datashets.impls.helpers.gds.DatashetsGDSTranscoder;
import rebound.datashets.impls.localfile.DatashetsLocalFileFormatTranscoder;
import rebound.exceptions.BinarySyntaxException;
import rebound.exceptions.GenericDataStructuresFormatException;

public enum DatashetsLocalFileFormatTranscoderForJSON
implements DatashetsLocalFileFormatTranscoder
{
	I;
	
	
	@Override
	public DatashetsTable read(InputStream in) throws IOException, BinarySyntaxException
	{
		Object gds = JSONUtilities.parseJSONfromUTF8(in);
		
		try
		{
			return DatashetsGDSTranscoder.decode(gds);
		}
		catch (GenericDataStructuresFormatException exc)
		{
			throw BinarySyntaxException.inst(exc);
		}
	}
	
	
	@Override
	public void write(DatashetsTable data, OutputStream out) throws IOException
	{
		Object gds = DatashetsGDSTranscoder.encode(data);
		JSONUtilities.serializeJSONForHumansToUTF8(gds, out);
	}
}
