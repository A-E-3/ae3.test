# ae3.test

The AE3 framework's own test suite: plain `main()` test classes (no JUnit) under `java/ru/myx/ae3/test/**`, covering base, eval, ecma, exec, vfs, s4, xml, io, know and layout. The `hlp/` tree holds helper comparison classes, and `test/` holds the tests that need the wider project set, such as PDF rendering.

Requires the rest of the framework — see `project.inf` for the full list, and `MAGIC.md` for how to run a test against the Eclipse-compiled `bin/` output.
