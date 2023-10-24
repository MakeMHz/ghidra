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
package coffprocesscv.pdbreader.type;

import coffprocesscv.pdbreader.*;

/**
 * This class represents various flavors of Default Arguments type.
 * <P>
 * Note: we do not necessarily understand each of these data type classes.  Refer to the
 *  base class for more information.
 */
public abstract class AbstractDefaultArgumentsMsType extends AbstractMsType {

	protected RecordNumber typeRecordNumber;
	protected String expression;

	/**
	 * Constructor for this type.
	 * @param pdb {@link AbstractPdb} to which this type belongs.
	 * @param reader {@link PdbByteReader} from which this type is deserialized.
	 * @param recordNumberSize size of record number to parse.
	 * @param strType {@link StringParseType} to use.
	 * @throws PdbException upon error parsing a field.
	 */
	public AbstractDefaultArgumentsMsType(AbstractPdb pdb, PdbByteReader reader,
			int recordNumberSize, StringParseType strType) throws PdbException {
		super(pdb, reader);
		typeRecordNumber = RecordNumber.parse(pdb, reader, RecordCategory.TYPE, recordNumberSize);
		expression = reader.parseString(pdb, strType);
	}

	@Override
	public void emit(StringBuilder builder, Bind bind) {
		builder.append(pdb.getTypeRecord(typeRecordNumber));
		builder.append(" ");
		builder.append(expression);
	}

}
