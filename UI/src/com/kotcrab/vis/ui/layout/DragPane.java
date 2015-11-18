/*
 * Copyright 2014-2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotcrab.vis.ui.layout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kotcrab.vis.ui.widget.Draggable;
import com.kotcrab.vis.ui.widget.Draggable.DragListener;

/**
 * Stores actors in an internally managed {@link WidgetGroup}. Allows actors with specialized {@link Draggable} listener attached
 * to be dropped and added into its group's content.
 *
 * <p>
 * Note that unless {@link Draggable} with appropriate listener (preferably {@link DefaultDragListener}) is attached to dragged
 * actors, this widget will act like a regular group with no extra functionalities. It's usually a good idea to use
 * {@link #setDraggable(Draggable)} method, as it will attach the listener to all its children, making them all draggable. If you
 * want to filter widgets accepted by this pane, use {@link #setListener(DragPaneListener)} method.
 *
 * @author MJ
 * @see #setDraggable(Draggable)
 * @see #setListener(DragPaneListener)
 * @since 0.9.3
 */
public class DragPane extends Container<WidgetGroup> {
	private Draggable draggable;
	private DragPaneListener listener;

	/** Creates a new horizontal drag pane. */
	public DragPane () {
		this(false);
	}

	/** @param vertical if true, actors will be stored vertically, if false - horizontally. */
	public DragPane (final boolean vertical) {
		this(vertical ? new VerticalGroup() : new HorizontalGroup());
	}

	/**
	 * @param group must append its actors through standard {@link WidgetGroup#addActor(Actor)} method. Must support
	 *           {@link WidgetGroup#addActorAfter(Actor, Actor)} and {@link WidgetGroup#addActorBefore(Actor, Actor)} methods. Note
	 *           that {@link com.badlogic.gdx.scenes.scene2d.ui.Table} does not meet these requirements.
	 * @see VerticalGroup
	 * @see HorizontalGroup
	 * @see GridGroup
	 */
	public DragPane (final WidgetGroup group) {
		if (group == null) {
			throw new IllegalArgumentException("Group cannot be null.");
		}
		super.setActor(group);
		setTouchable(Touchable.enabled);
	}

	/**
	 * @return true if children are displayed vertically in a {@link VerticalGroup}.
	 * @see #getVerticalGroup()
	 */
	public boolean isVertical () {
		return getActor() instanceof VerticalGroup;
	}

	/**
	 * @return true if children are displayed horizontally in a {@link HorizontalGroup}.
	 * @see #getHorizontalGroup()
	 */
	public boolean isHorizontal () {
		return getActor() instanceof HorizontalGroup;
	}

	/**
	 * @return true if children are displayed as a grid in a {@link GridGroup}.
	 * @see #getGridGroup()
	 */
	public boolean isGrid () {
		return getActor() instanceof GridGroup;
	}

	@Override
	public SnapshotArray<Actor> getChildren () {
		return getActor().getChildren();
	}

	/** @return internally managed group of actors. */
	public WidgetGroup getGroup () {
		return getActor();
	}

	/** @param group will replace the internally managed group. All current children will be moved to this group. */
	public void setGroup (final WidgetGroup group) {
		setActor(group);
	}

	/** @param group will replace the internally managed group. All current children will be moved to this group. */
	@Override
	public void setActor (final WidgetGroup group) {
		if (group == null) {
			throw new IllegalArgumentException("Group cannot be null.");
		}
		final Group previousGroup = getActor();
		super.setActor(group);
		attachListener(); // Attaches draggable to all previous group children.
		for (final Actor child : previousGroup.getChildren()) {
			group.addActor(child); // No need to attach draggable, child was already in pane.
		}
	}

	/**
	 * @return internally managed group of actors.
	 * @throws ClassCastException if drag pane is not horizontal.
	 * @see #isHorizontal()
	 */
	public HorizontalGroup getHorizontalGroup () {
		return (HorizontalGroup)getActor();
	}

	/**
	 * @return internally managed group of actors.
	 * @throws ClassCastException if drag pane is not vertical.
	 * @see #isVertical()
	 */
	public VerticalGroup getVerticalGroup () {
		return (VerticalGroup)getActor();
	}

	/**
	 * @return internally managed group of actors.
	 * @throws ClassCastException if drag pane is not a grid.
	 * @see #isGrid()
	 */
	public GridGroup getGridGroup () {
		return (GridGroup)getActor();
	}

	/** @return dragging listener automatically added to all panes' children. */
	public Draggable getDraggable () {
		return draggable;
	}

	/** @param draggable will be automatically added to all children. */
	public void setDraggable (final Draggable draggable) {
		removeListener();
		this.draggable = draggable;
		attachListener();
	}

	@Override
	public void setBounds (final float x, final float y, final float width, final float height) {
		super.setBounds(x, y, width, height);
		getActor().setWidth(width);
		getActor().setHeight(height);
		// Child position omitted on purpose.
	}

	@Override
	public void setWidth (final float width) {
		super.setWidth(width);
		getActor().setWidth(width);
	}

	@Override
	public void setHeight (final float height) {
		super.setHeight(height);
		getActor().setHeight(height);
	}

	private void removeListener () {
		if (draggable == null) {
			return;
		}
		for (final Actor actor : getChildren()) {
			actor.removeListener(draggable);
		}
	}

	private void attachListener () {
		if (draggable == null) {
			return;
		}
		for (final Actor actor : getChildren()) {
			draggable.attachTo(actor);
		}
	}

	/**
	 * @param actor might be in the drag pane.
	 * @return true if actor is added to the pane's internal group.
	 */
	public boolean contains (final Actor actor) {
		return actor.getParent() == getActor();
	}

	@Override
	public boolean removeActor (final Actor actor) {
		return getActor().removeActor(actor);
	}

	@Override
	public boolean removeActor (final Actor actor, final boolean unfocus) {
		return getActor().removeActor(actor, unfocus);
	}

	@Override
	public void clear () {
		getActor().clear();
	}

	@Override
	public void addActor (final Actor actor) {
		getActor().addActor(actor);
		doOnAdd(actor);
	}

	@Override
	public void addActorAfter (final Actor actorAfter, final Actor actor) {
		getActor().addActorAfter(actorAfter, actor);
		doOnAdd(actor);
	}

	@Override
	public void addActorAt (final int index, final Actor actor) {
		getActor().addActorAt(index, actor);
		doOnAdd(actor);
	}

	@Override
	public void addActorBefore (final Actor actorBefore, final Actor actor) {
		getActor().addActorBefore(actorBefore, actor);
		doOnAdd(actor);
	}

	/** @param actor was just added to the group. */
	protected void doOnAdd (final Actor actor) {
		if (draggable != null) {
			draggable.attachTo(actor);
		}
	}

	@Override
	public <T extends Actor> T findActor (final String name) {
		return getActor().findActor(name);
	}

	@Override
	public void invalidate () {
		super.invalidate();
		getActor().invalidate();
	}

	@Override
	public void validate () {
		super.validate();
		getActor().validate();
	}

	/** @param listener manages children appended to the drag pane. */
	public void setListener (final DragPaneListener listener) {
		this.listener = listener;

	}

	/**
	 * @param actor is dragged over the pane.
	 * @return true if actor can be added to the pane.
	 */
	protected boolean accept (final Actor actor) {
		return listener == null || listener.accept(this, actor);
	}

	/**
	 * Default {@link DragListener} implementation. Implements {@link DragPane} behavior.
	 *
	 * @author MJ
	 * @since 0.9.3
	 */
	public static class DefaultDragListener implements DragListener {
		private static final Vector2 DRAG_POSITION = new Vector2();
		private Policy policy;

		/** Creates a new drag listener with default policy. */
		public DefaultDragListener () {
			this(DefaultPolicy.ALLOW_REMOVAL);
		}

		/**
		 * @param policy determines behavior of dragged actors. Allows to prohibit actors from being added to a {@link DragPane}.
		 *           Cannot be null.
		 * @see #setPolicy(Policy)
		 */
		public DefaultDragListener (final Policy policy) {
			setPolicy(policy);
		}

		/**
		 * @param policy determines behavior of dragged actors. Allows to prohibit actors from being added to a {@link DragPane}.
		 *           Cannot be null.
		 * @see DefaultPolicy
		 */
		public void setPolicy (final Policy policy) {
			if (policy == null) {
				throw new IllegalArgumentException("Policy cannot be null.");
			}
			this.policy = policy;
		}

		@Override
		public boolean onStart (final Actor actor, final float stageX, final float stageY) {
			return APPROVE;
		}

		@Override
		public void onDrag (final Actor actor, final float stageX, final float stageY) {
		}

		@Override
		public boolean onEnd (final Actor actor, final float stageX, final float stageY) {
			final Actor overActor = actor.getStage().hit(stageX, stageY, true);
			if (overActor == null || overActor == actor || overActor.isAscendantOf(actor)) {
				return CANCEL;
			}
			if (overActor instanceof DragPane) {
				// Dragged directly to a pane. Assuming no padding, adding last.
				if (accept(overActor, (DragPane)overActor)) {
					((DragPane)overActor).addActor(actor);
					return APPROVE;
				}
				return CANCEL;
			}
			final DragPane dragPane = getDragPane(overActor);
			if (accept(actor, dragPane)) {
				DRAG_POSITION.set(stageX, stageY);
				addActor(actor, overActor, dragPane);
				return APPROVE;
			}
			return CANCEL;
		}

		/**
		 * @param actor has just been dragged.
		 * @param dragPane is under the dragged actor (if exists). Can be null.
		 * @return true if the actor can be added to the dragPane.
		 */
		protected boolean accept (final Actor actor, final DragPane dragPane) {
			return dragPane != null && dragPane.accept(actor) && policy.accept(dragPane, actor);
		}

		private void addActor (final Actor actor, final Actor overActor, final DragPane dragPane) {
			final Actor directPaneChild = getActorInDragPane(overActor, dragPane);
			directPaneChild.stageToLocalCoordinates(DRAG_POSITION);
			if (dragPane.isVertical()) {
				addToVerticalGroup(actor, dragPane, directPaneChild);
			} else if (dragPane.isHorizontal()) {
				addToHorizontalGroup(actor, dragPane, directPaneChild);
			} else { // This is the default behavior for grid and unknown groups.
				addToOtherGroup(actor, dragPane, directPaneChild);
			}
		}

		/**
		 * @param actor is being dragged.
		 * @param dragPane is under the actor. Stores a {@link HorizontalGroup}.
		 * @param directPaneChild actor under the cursor.
		 */
		protected void addToHorizontalGroup (final Actor actor, final DragPane dragPane, final Actor directPaneChild) {
			final Array<Actor> children = dragPane.getChildren();
			final int indexOfDraggedActor = children.indexOf(actor, true);
			if (indexOfDraggedActor >= 0) {
				final int indexOfDirectChild = children.indexOf(directPaneChild, true);
				if (indexOfDirectChild > indexOfDraggedActor) {
					dragPane.addActorAfter(directPaneChild, actor);
				} else {
					dragPane.addActorBefore(directPaneChild, actor);
				}
			} else if (DRAG_POSITION.x > directPaneChild.getWidth() / 2f) {
				dragPane.addActorAfter(directPaneChild, actor);
			} else {
				dragPane.addActorBefore(directPaneChild, actor);
			}
		}

		/**
		 * @param actor is being dragged.
		 * @param dragPane is under the actor. Stores a {@link VerticalGroup}.
		 * @param directPaneChild actor under the cursor.
		 */
		protected void addToVerticalGroup (final Actor actor, final DragPane dragPane, final Actor directPaneChild) {
			final Array<Actor> children = dragPane.getChildren();
			final int indexOfDraggedActor = children.indexOf(actor, true);
			if (indexOfDraggedActor >= 0) {
				final int indexOfDirectChild = children.indexOf(directPaneChild, true);
				if (indexOfDirectChild > indexOfDraggedActor) {
					dragPane.addActorAfter(directPaneChild, actor);
				} else {
					dragPane.addActorBefore(directPaneChild, actor);
				}
			} else if (DRAG_POSITION.y < directPaneChild.getHeight() / 2f) { // Y inverted.
				dragPane.addActorAfter(directPaneChild, actor);
			} else {
				dragPane.addActorBefore(directPaneChild, actor);
			}
		}

		/**
		 * @param actor is being dragged.
		 * @param dragPane is under the actor. Stores a {@link GridGroup} or unknown group.
		 * @param directPaneChild actor under the cursor.
		 */
		protected void addToOtherGroup (final Actor actor, final DragPane dragPane, final Actor directPaneChild) {
			final Array<Actor> children = dragPane.getChildren();
			final int indexOfDirectChild = children.indexOf(directPaneChild, true);
			final int indexOfDraggedActor = children.indexOf(actor, true);
			if (indexOfDraggedActor >= 0) { // Dragging own actor.
				if (indexOfDraggedActor > indexOfDirectChild) { // Dropped after current position.
					dragPane.addActorBefore(directPaneChild, actor);
				} else { // Dropped before current position.
					dragPane.addActorAfter(directPaneChild, actor);
				}
			} else if (indexOfDirectChild == children.size - 1) { // Dragged into last element.
				if (DRAG_POSITION.y < directPaneChild.getHeight() / 2f || DRAG_POSITION.x > directPaneChild.getWidth() / 2f) { // Adding
																																									// last:
					dragPane.addActor(actor);
				} else {
					dragPane.addActorBefore(directPaneChild, actor);
				}
			} else if (indexOfDirectChild == 0) { // Dragged into first element.
				if (DRAG_POSITION.y < directPaneChild.getHeight() / 2f || DRAG_POSITION.x > directPaneChild.getWidth() / 2f) {
					dragPane.addActorAfter(directPaneChild, actor);
				} else { // Adding first:
					dragPane.addActorBefore(directPaneChild, actor);
				}
			} else { // Replacing hovered actor:
				dragPane.addActorBefore(directPaneChild, actor);
			}
		}

		/**
		 * @param actor if in the drag pane, but does not have to be added directly.
		 * @param dragPane contains the actor.
		 * @return passed actor or the parent of the actor added directly to the pane.
		 */
		protected Actor getActorInDragPane (Actor actor, final DragPane dragPane) {
			while (actor != dragPane && actor != null) {
				if (dragPane.contains(actor)) {
					return actor;
				} // Actor might not be added directly to the drag pane. Trying out the parent:
				actor = actor.getParent();
			}
			return null;
		}

		/**
		 * @param fromActor might be in a drag pane.
		 * @return drag pane parent or null.
		 */
		protected DragPane getDragPane (Actor fromActor) {
			while (fromActor != null) {
				if (fromActor instanceof DragPane) {
					return (DragPane)fromActor;
				}
				fromActor = fromActor.getParent();
			}
			return null;
		}

		/**
		 * Determines behavior of {@link DefaultDragListener}.
		 *
		 * @author MJ
		 * @since 0.9.3
		 */
		public static interface Policy {
			/**
			 * @param dragPane is under the actor.
			 * @param actor was dragged into the drag pane.
			 * @return true if the actor can be added to the {@link DragPane}
			 */
			boolean accept (DragPane dragPane, Actor actor);
		}

		/**
		 * Contains basic {@link DefaultDragListener} behaviors, allowing to modify the listener without extending it.
		 *
		 * @author MJ
		 * @since 0.9.3
		 */
		public static enum DefaultPolicy implements Policy {
			/** Allows children to be moved to different {@link DragPane}s. */
			ALLOW_REMOVAL {
				@Override
				public boolean accept (final DragPane dragPane, final Actor actor) {
					return APPROVE;
				}
			},
			/** Prohibits from removing children from the {@link DragPane}, allowing them only to be moved within their own group. */
			KEEP_CHILDREN {
				@Override
				public boolean accept (final DragPane dragPane, final Actor actor) {
					return dragPane.contains(actor); // dragPane must be the direct parent of actor.
				}
			};
		}
	}

	/**
	 * Allows to select children added to the group.
	 *
	 * @author MJ
	 * @since 0.9.3
	 */
	public static interface DragPaneListener {
		/** Return in {@link #accept(DragPane, Actor)} method for code clarity. */
		boolean ACCEPT = true, REFUSE = false;

		/**
		 * @param dragPane has this listener attached.
		 * @param actor if being dragged over the {@link DragPane}.
		 * @return true if actor can be added to the drag pane. False if it cannot.
		 */
		boolean accept (DragPane dragPane, Actor actor);

		/**
		 * When actors are dragged into the {@link DragPane}, they are accepted and added into the pane only if their direct parent
		 * is the pane itself.
		 *
		 * @author MJ
		 * @since 0.9.3
		 */
		public static class AcceptOwnChildren implements DragPaneListener {
			@Override
			public boolean accept (final DragPane dragPane, final Actor actor) {
				return dragPane.contains(actor);
			}
		}

		/**
		 * Limits {@link DragPane} children amount to a certain number.
		 *
		 * @author MJ
		 * @since 0.9.3
		 */
		public static class LimitChildren implements DragPaneListener {
			private final int max;

			/**
			 * @param max if {@link DragPane}'s children amount equals (or is greater than) this value, other children will not be
			 *           accepted.
			 */
			public LimitChildren (final int max) {
				this.max = max;
			}

			@Override
			public boolean accept (final DragPane dragPane, final Actor actor) {
				return dragPane.getChildren().size < max;
			}
		}
	}
}
