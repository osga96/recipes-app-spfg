package com.example.learn.recipes_app.converters;

import com.example.learn.recipes_app.commands.NotesCommand;
import com.example.learn.recipes_app.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class NotesToNotesCommand implements Converter<Note, NotesCommand>{

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Note source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
