# Assignment 7 — Ownership and Permissions

**Student:** _Name Surname_  **Group:** _____  **Course:** Operating Systems, Astana IT University

## 1. Purpose of the work

To learn how Linux controls access to files and directories: who owns a file
(user and group), what the permission bits `r`, `w` and `x` mean for the owner,
the group and others, and how to change them with `chown`, `chgrp` and `chmod`
in both symbolic and numeric modes. The work also covers the special `setgid`
bit on directories.

## 2. NDG Linux Essentials

### 2.1–2.2 Chapter 17 theory and Exam 17

> 📷 **Screenshot:** Exam 17 result page showing the grade, the attempt number and your login name.

### 2.3 Lab 17

For every lab step: run the command in your VM, take a screenshot where your
name is visible (in the prompt, e.g. `aidana_serikova@ubuntu:~$`), and write one or two
sentences on what the command did.

> 📷 **Screenshot 1:** …
> **Explanation:** …

## 3. Additional tasks

> **Tip so your name shows in every screenshot:** run
> `export PS1="Name_Surname@\h:\w\$ "` first, or log in as a user named after you.
> Commands that create users or change owners need `sudo`.

### Task 1. Create a test directory

```bash
sudo mkdir /home/projectX
sudo touch /home/projectX/report.txt /home/projectX/data.csv
ls -l /home/projectX
```

`mkdir` creates the directory and `touch` creates two empty files. At this point
the owner and group of both files are `root`, because `sudo` created them.

> 📷 Screenshot

### Task 2. Set ownership

```bash
sudo useradd team1
sudo groupadd projectgroup
sudo chown team1:projectgroup /home/projectX/report.txt /home/projectX/data.csv
ls -l /home/projectX
```

`useradd` creates the user `team1` and `groupadd` creates the group
`projectgroup`. `chown user:group file` changes the owner and the group in one
command. You could also do it in two steps: `chown team1 file` and then
`chgrp projectgroup file`.

> 📷 Screenshot

### Task 3. Change permissions

Required: owner `rwx`, group `r-x`, others `---`.

```bash
sudo chmod u=rwx,g=rx,o= /home/projectX/report.txt /home/projectX/data.csv
```

### Task 4. Verify

```bash
ls -l /home/projectX
```

Expected output:

```
-rwxr-x--- 1 team1 projectgroup 0 ... data.csv
-rwxr-x--- 1 team1 projectgroup 0 ... report.txt
```

How to read `-rwxr-x---`:

| Position | Characters | Meaning |
|---|---|---|
| 1 | `-` | File type: `-` is a regular file, `d` is a directory, `l` is a link |
| 2–4 | `rwx` | **Owner** (team1) can read, write and execute |
| 5–7 | `r-x` | **Group** (projectgroup) can read and execute but not write |
| 8–10 | `---` | **Others** have no access |

In numbers, r = 4, w = 2, x = 1, so `rwx` = 7, `r-x` = 5 and `---` = 0. That
gives **750**.

> 📷 Screenshot

### Task 5. Set directory inheritance (setgid)

```bash
sudo chgrp projectgroup /home/projectX
sudo chmod g+s /home/projectX
ls -ld /home/projectX
sudo touch /home/projectX/newfile.txt
ls -l /home/projectX
```

Expected output:

```
drwxr-sr-x 2 root projectgroup ... /home/projectX
-rw-r--r-- 1 root projectgroup ... newfile.txt
```

The `s` in the group's execute position (`r-s`) shows that setgid is on. A file
created in a setgid directory takes the **directory's group** instead of the
creator's main group. That is why `newfile.txt` belongs to `projectgroup` even
though `root` created it. This setup is useful for team folders, because every
new file is shared with the team automatically.

> 📷 Screenshot

### Task 6. Symbolic and numeric modes

**Symbolic mode:** you name who (`u` user, `g` group, `o` others, `a` all), give
an operator (`+` add, `-` remove, `=` set exactly) and list the permissions.

```bash
sudo chmod g+w /home/projectX/report.txt
ls -l /home/projectX/report.txt     # -rwxrwx---
```

`g+w` adds write permission for the group. All other bits stay the same.

**Numeric (octal) mode:** you set all three groups at once with digits, where each
digit is the sum of r = 4, w = 2 and x = 1.

```bash
sudo chmod 750 /home/projectX/report.txt
ls -l /home/projectX/report.txt     # -rwxr-x---
```

`750` means owner 7 = `rwx`, group 5 = `r-x` and others 0 = `---`. It replaces
every bit, so the group's write permission from the previous step is removed.

| | Symbolic | Numeric |
|---|---|---|
| What it changes | Only the bits you name | All bits at once |
| Example | `chmod g+w file` | `chmod 750 file` |
| Best for | Small changes | Setting an exact, known state |

> 📷 Screenshots

## 4. Conclusion

In this work I learned that every Linux file has an owning user and an owning
group, and that access is controlled by three sets of `r`/`w`/`x` bits for the
owner, the group and others. I changed ownership with `chown` and `chgrp`, and
changed permissions with `chmod` in both symbolic (`g+w`) and numeric (`750`)
form. I also used the setgid bit so that new files in a shared directory inherit
its group automatically.
