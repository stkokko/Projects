PGDMP     ;                    x           MovieNights    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16393    MovieNights    DATABASE     �   CREATE DATABASE "MovieNights" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Greek_Greece.1252' LC_CTYPE = 'Greek_Greece.1252';
    DROP DATABASE "MovieNights";
                postgres    false            �            1259    16422 	   bookmarks    TABLE     x   CREATE TABLE public.bookmarks (
    email character varying(100) NOT NULL,
    title character varying(100) NOT NULL
);
    DROP TABLE public.bookmarks;
       public         heap    postgres    false            �            1259    16417    users    TABLE     n   CREATE TABLE public.users (
    email character varying(100) NOT NULL,
    password character varying(100)
);
    DROP TABLE public.users;
       public         heap    postgres    false                      0    16422 	   bookmarks 
   TABLE DATA           1   COPY public.bookmarks (email, title) FROM stdin;
    public          postgres    false    203   �
                 0    16417    users 
   TABLE DATA           0   COPY public.users (email, password) FROM stdin;
    public          postgres    false    202   �       �
           2606    16440    bookmarks bookmarks_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmarks_pkey PRIMARY KEY (email, title);
 B   ALTER TABLE ONLY public.bookmarks DROP CONSTRAINT bookmarks_pkey;
       public            postgres    false    203    203            �
           2606    16442    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    202            �
           2606    16443    bookmarks bookmarks_email_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmarks_email_fkey FOREIGN KEY (email) REFERENCES public.users(email);
 H   ALTER TABLE ONLY public.bookmarks DROP CONSTRAINT bookmarks_email_fkey;
       public          postgres    false    203    2690    202               �   x�m���0Cg����N�v�C�:vIˑD��R��&C��d�϶,)PTo�i�Uk�GR��?��$���o]�vZ_��;�$oQ��5~̼L��4W���M���Άt��n����>Z9A�{L+��2PO���,/T�         6   x�+.�����w�L����K/�4�����Ԋ�܂�T���\NC0������ ���     