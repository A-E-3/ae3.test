# MAGIC.md — ae3.test

Repo notes for the team: what this project is, how to run a suite, and where things sit.

## What it is

The AE3 framework's own test suite. Every suite is a plain `main()` class, with no JUnit and no runner dependency. `ru.myx.ae3.test.TestAe3` calls 24 of them in a fixed order and prints `AE3 TEST PASSED!!!` only when all of them return.

## Running a suite

- **Assertions are mandatory.** `TestAe3.main` fails at once with `assertions required for this test!` when assertions are off. Pass `-ea` to `java`.
- **Working directory.** `TestAe3.initAE3()` derives four storage paths from `user.dir`'s parent, and sets `ru.myx.ae3.properties.path.public`, `.protected`, `.private` and `.shared` from them. The public one must exist and hold an `axiom` folder, or the guard asserts. Run with the working directory one level below the tree that holds those siblings.
- **Order inside `initAE3()`.** It sets `ru.myx.ae3.properties.vfs.storage=ramfs:storage` and log level `DEBUG`, then calls `Engine.createGuid()` before `ImplAE3.init()`. Keep that order when you reuse the setup.
- **`initAE3()` is idempotent.** It sets the `ae3` system property and returns early on a second call.

## Where things live

- `java/ru/myx/ae3/test/**` — the suites, one package per area, in `TestAe3`'s own order: base, engine random and guid entropy, act, binary, io, util, help, know, answer, auth, reflect, exec, java, s4, vfs, flow, produce, mime, transform, xml, eval, ecma, layout.
- `hlp/` — helper classes, not suites. Alternative map implementations beside the `Test*Maps` enums that fill and compare them.
- `test/` — the suites needing a wider project set, PDF rendering among them.
- `ae3-packages/ae3.test/` — the packaged layout. `settings/tests/*.json` holds the descriptors, `settings/shell/test.json` points at the script that reads them, and `resources/` holds the fixtures: V8 benchmark scripts, an XML sample and a command test script.
- `try/` — an empty scratch source folder, kept on the classpath.

## The JSON descriptors

Each `settings/tests/*.json` carries `type : "ae3.Test"` with either `java : "<class name>"` or `file : "<resource path>"`. `resources/lib/ae3/internal/test/CommandTest.js` reads them in order of appearance and skips every other `type`.

- `vfs.json` declares `type : "ae3.Test"` and names neither a class nor a file. A reader of these descriptors must tolerate that.

## Gotchas

- **`RUN-AE3-TEST.xml` is zero bytes.** It is a placeholder, not a runner. Do not look for ant targets in it.
- **`.classpath` resolves dependencies by project name.** It names four source folders and one project reference per dependency, with output in `bin`. It builds in an Eclipse workspace holding those projects.
- **Launch files.** `TestEcma.launch`, `TestReflect.launch`, `TestS4.launch`, `TestVfs.launch` and `test/ru/myx/ae3/l2/pdf/test/TestPdf.launch` carry per-suite run configurations.
