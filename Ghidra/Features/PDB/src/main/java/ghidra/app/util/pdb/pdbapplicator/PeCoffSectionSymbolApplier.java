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
package coffprocesscv.pdbapplicator;

import coffprocesscv.pdbreader.PdbException;
import coffprocesscv.pdbreader.symbol.AbstractMsSymbol;
import coffprocesscv.pdbreader.symbol.PeCoffSectionMsSymbol;
import coffprocesscv.pdbapplicator.SymbolGroup.AbstractMsSymbolIterator;
import ghidra.util.exception.AssertException;
import ghidra.util.exception.CancelledException;

/**
 * Applier for {@link PeCoffSectionMsSymbol} symbols.
 */
public class PeCoffSectionSymbolApplier extends MsSymbolApplier {

	private PeCoffSectionMsSymbol symbol;

	/**
	 * Constructor
	 * @param applicator the {@link DefaultPdbApplicator} for which we are working.
	 * @param iter the Iterator containing the symbol sequence being processed
	 */
	public PeCoffSectionSymbolApplier(DefaultPdbApplicator applicator,
			AbstractMsSymbolIterator iter) {
		super(applicator, iter);
		AbstractMsSymbol abstractSymbol = iter.next();
		if (!(abstractSymbol instanceof PeCoffSectionMsSymbol)) {
			throw new AssertException(
				"Invalid symbol type: " + abstractSymbol.getClass().getSimpleName());
		}
		symbol = (PeCoffSectionMsSymbol) abstractSymbol;
	}

	@Override
	void apply() throws PdbException, CancelledException {
		int sectionNum = symbol.getSectionNumber();
		long realAddress = symbol.getRva();
		symbol.getLength();
		symbol.getCharacteristics();
		symbol.getAlign();
		symbol.getName();
		// 20220712: The gathering of these and other Linker symbols has been moved to a special
		// PdbApplicator method.
		// We need to revisit what work we would like done here (and in PeCoffGroupSymbolApplier).
//		applicator.putRealAddressesBySection(sectionNum, realAddress);
//		applicator.addMemorySectionRefinement(symbol);
	}

	@Override
	void applyTo(MsSymbolApplier applyToApplier) {
		// Do nothing
	}
}
