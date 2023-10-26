/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package coffprocesscv.pdbreader;

import java.io.IOException;
import java.io.Writer;

/**
 * This class is the version of {@link TypeProgramInterface} for embedded CodeView data.
 */
public class TypeProgramInterfaceCV extends TypeProgramInterface {

	//==============================================================================================
	// Internals
	//==============================================================================================
	protected int hashStreamNumber;

	//==============================================================================================
	// API
	//==============================================================================================
	/**
	 * Constructor
	 * @param pdb {@link AbstractPdb} that owns this {@link TypeProgramInterface}
	 * @param recordCategory the RecordCategory of these records
	 * @param streamNumber the stream number that contains the {@link TypeProgramInterface}
	 */
	public TypeProgramInterfaceCV(AbstractPdb pdb, RecordCategory recordCategory, int streamNumber) {
		super(pdb, recordCategory, streamNumber);
	}

	//==============================================================================================
	// Abstract Methods
	//==============================================================================================
	@Override
	protected void deserializeHeader(PdbByteReader reader) throws PdbException {
		versionNumber = reader.parseInt();
		headerLength = 4; 
		typeIndexMin = typeIndexMaxExclusive = 0x1000;
		reader.align4();
		
		// Calculate TypeIndexMaxExclusive
		int savedIndex = reader.getIndex();
		while(reader.hasMore()) {
			reader.skip(reader.parseUnsignedShortVal());
			typeIndexMaxExclusive++;
		}
		reader.setIndex(savedIndex);
	}

	@Override
	protected void dumpHeader(Writer writer) throws IOException {

	}

	//==============================================================================================
	// Package-Protected Internals
	//==============================================================================================
	/**
	 * IMPORTANT: This method is for testing only.  It allows us to set a basic object.
	 * <p>
	 * Note: not all values are initialized.  This is a dummy constructor used to create a dummy
	 * {@link TypeProgramInterface}.
	 * <p>
	 * Note: not all values are initialized.
	 * @param pdb {@link AbstractPdb} that owns this {@link TypeProgramInterface}
	 * @param typeIndexMin the IndexMin to set/use
	 * @param typeIndexMaxExclusive one greater than the MaxIndex to set/use
	 */
	TypeProgramInterfaceCV(AbstractPdb pdb, int typeIndexMin, int typeIndexMaxExclusive) {
		super(pdb, typeIndexMin, typeIndexMaxExclusive);
	}

}
