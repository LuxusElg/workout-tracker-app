Relations between entities:

Should exercises be tied to exercise templates by foreign key constraints?
What if a template is deleted? That should not remove logged exercises...

For now: using foreignkeys but not ondelete:cascade
