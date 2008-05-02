/*
 * Copyright (c) 2007-2008 Chris K Wensel. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package cascading.flow.stack;

import cascading.flow.FlowElement;
import cascading.flow.Scope;
import cascading.pipe.Each;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

/**
 *
 */
class EachMapperStackElement extends MapperStackElement
  {
  private final Each each;

  public EachMapperStackElement( MapperStackElement previous, Scope incomingScope, Each each )
    {
    super( previous, incomingScope );
    this.each = each;
    }

  protected FlowElement getFlowElement()
    {
    return each;
    }

  @Override
  public void collect( Tuple tuple )
    {
    super.collect( tuple );

    operateEach( getTupleEntry( tuple ) );
    }

  private void operateEach( TupleEntry tupleEntry )
    {
    each.operate( ( (MapperStackElement) next ).getIncomingScope(), tupleEntry, next );
    }
  }